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
import java.util.HashMap;
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
    public ResponseEntity<Object> pickupList(@RequestBody Criteria cri) throws Exception{

        Map<String, Object> response = new HashMap<>();
        int total = crewService.getTotalToBeCollect(cri);

        response.put("collectList", crewService.getToBeCollectListWithPaging(cri));
        response.put("pageMaker", new PagenationDTO(cri, total));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/to-be")
    public ResponseEntity<Object> toBeDeliveryList(@RequestBody Criteria cri) throws Exception {

        Map<String, Object> response = new HashMap<>();
        int total = crewService.getTotalToBeDelivery(cri);

        response.put("toBeDeliveryList", crewService.getWcListWithPaging(cri));
        response.put("pageMaker", new PagenationDTO(cri, total));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/in-process")
    public ResponseEntity<Object> inProcessDeliveryList(@RequestBody Criteria cri, @AuthenticationPrincipal UserDTO user) throws Exception {
//        UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user + "21111111111111111111111111111111111111111111111111111111111111111");
        CrewDTO crew = crewService.getCrew(user.getUserId());

        Map<String, Object> response = new HashMap<>();
        int total = crewService.getDeliveryList(crew.getCrewId(), "배달중").size();

        response.put("DeliveringList", crewService.getDeliveryListWithPaging(crew.getCrewId(), "배달중", cri));
        response.put("pageMaker", new PagenationDTO(cri, total));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/completed")
    public ResponseEntity<Object> completedDeliveryList(@RequestBody Criteria cri, @AuthenticationPrincipal UserDTO user) throws Exception {
        CrewDTO crew = crewService.getCrew(user.getUserId());

        Map<String, Object> response = new HashMap<>();
        int total = crewService.getDeliveryList(crew.getCrewId(), "배달완료").size();

        response.put("completedDeliveryList", crewService.getDeliveryListWithPaging(crew.getCrewId(), "배달완료", cri));
        response.put("pageMaker", new PagenationDTO(cri, total));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/collect/{rid}")
    public ResponseEntity<String> collect(@PathVariable Long rid, @RequestBody CrewDTO crew) throws  Exception {

        return rcService.insertReservationConfirmed(rid, crew) != null ? ResponseEntity.status(HttpStatus.OK).body("success")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");

        //return ResponseEntity.status(HttpStatus.OK).build(); // String flag를 뻇음. front에서 redirect 시켜야할듯
    }

    @PostMapping("/process/{rid}")
    public ResponseEntity<String> process(@PathVariable Long rid, @RequestBody Map<String, Long> idMap) throws Exception{

        CrewDTO crewDTO = new CrewDTO();
        crewDTO.setCrewId(idMap.get("crewId"));

        WashingCompleteDTO washingCompleteDTO = new WashingCompleteDTO();
        washingCompleteDTO.setWcId(idMap.get("wcId"));

        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setCrew(crewDTO);
        deliveryDTO.setWc(washingCompleteDTO);

        return crewService.updateDelivering(rid, deliveryDTO) == true ? ResponseEntity.status(HttpStatus.OK).body("success")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        // String flag를 뻇음. front에서 redirect 시켜야할듯
    }

    @PostMapping("/completed/{rid}")
    public ResponseEntity<String> completed(@PathVariable Long rid, @RequestBody Map<String, Long> idMap) throws  Exception{
        return crewService.updateResDoneDelivery(rid, idMap.get("deliveryId")) == true ? ResponseEntity.status(HttpStatus.OK).body("success")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        // String flag를 뻇음. front에서 redirect 시켜야할듯
    }





}
