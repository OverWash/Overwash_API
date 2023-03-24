package com.meta.overwash.api;

import com.meta.overwash.domain.*;
import com.meta.overwash.service.CrewService;
import com.meta.overwash.service.ReservationConfirmedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private CrewService crewService;

    @Autowired
    private ReservationConfirmedService rcService;

    @GetMapping("/pickup-list")
    public List<ReservationDTO> pickupList() throws Exception{
        return crewService.getToBeCollectList();
    }

    @GetMapping("/to-be")
    public List<WashingCompleteDTO> toBeDeliveryList() throws Exception {
        return crewService.getWcList();
    }

    @GetMapping("/in-process")
    public List<DeliveryDTO> inProcessDeliveryList(@AuthenticationPrincipal UserDTO user) throws Exception {
//        UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user + "21111111111111111111111111111111111111111111111111111111111111111");
        CrewDTO crew = crewService.getCrew(user.getUserId());

        return crewService.getDeliveryList(crew.getCrewId(), "배달중");
    }

    @GetMapping("/completed")
    public List<DeliveryDTO> completedDeliveryList(@AuthenticationPrincipal UserDTO user) throws Exception {
        CrewDTO crew = crewService.getCrew(user.getUserId());

        return crewService.getDeliveryList(crew.getCrewId(), "배달완료");
    }

    @PostMapping("/collect/{rid}")
    public ResponseEntity<Void> collect(@PathVariable Long rid, @RequestBody CrewDTO crew) throws  Exception {
        rcService.insertReservationConfirmed(rid, crew);

        return ResponseEntity.status(HttpStatus.OK).build(); // String flag를 뻇음. front에서 redirect 시켜야할듯
    }

    @PostMapping("/process/{rid}")
    public ResponseEntity<Void> process(@PathVariable Long rid, @RequestBody Map<String, Long> idMap) throws Exception{

        CrewDTO crewDTO = new CrewDTO();
        crewDTO.setCrewId(idMap.get("crewId"));

        WashingCompleteDTO washingCompleteDTO = new WashingCompleteDTO();
        washingCompleteDTO.setWcId(idMap.get("wcId"));

        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setCrew(crewDTO);
        deliveryDTO.setWc(washingCompleteDTO);

        crewService.updateDelivering(rid, deliveryDTO);
        return ResponseEntity.status(HttpStatus.OK).build(); // String flag를 뻇음. front에서 redirect 시켜야할듯
    }

    @PostMapping("/completed/{rid}")
    public ResponseEntity<Void> completed(@PathVariable Long rid, @RequestBody Map<String, Long> idMap) throws  Exception{
        crewService.updateResDoneDelivery(rid, idMap.get("deliveryId"));

        return ResponseEntity.status(HttpStatus.OK).build(); // String flag를 뻇음. front에서 redirect 시켜야할듯
    }





}
