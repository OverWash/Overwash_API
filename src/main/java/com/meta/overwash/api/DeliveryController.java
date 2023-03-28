package com.meta.overwash.api;

import com.meta.overwash.domain.*;
import com.meta.overwash.service.CrewService;
import com.meta.overwash.service.ReservationConfirmedService;
import com.meta.overwash.service.UserService;
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

    @Autowired
    private UserService userService;

    @PostMapping("/pickup-list")
    public ResponseEntity<Object> pickupList(@RequestBody Criteria cri) throws Exception{

        Map<String, Object> response = new HashMap<>();
        int total = crewService.getTotalToBeCollect(cri);

        response.put("collectList", crewService.getToBeCollectListWithPaging(cri));
        response.put("pageMaker", new PagenationDTO(cri, total));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/to-be")
    public ResponseEntity<Object> toBeDeliveryList(@RequestBody Criteria cri) throws Exception {

        Map<String, Object> response = new HashMap<>();
        int total = crewService.getTotalToBeDelivery(cri);

        response.put("wcList", crewService.getWcListWithPaging(cri));
        response.put("pageMaker", new PagenationDTO(cri, total));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/in-process")
    public ResponseEntity<Object> inProcessDeliveryList(@RequestBody Criteria cri, @AuthenticationPrincipal UserDTO user) throws Exception {
        CrewDTO crew = crewService.getCrew(user.getUserId());

        Map<String, Object> response = new HashMap<>();
        int total = crewService.getDeliveryList(crew.getCrewId(), "배달중").size();

        response.put("deliveringList", crewService.getDeliveryListWithPaging(crew.getCrewId(), "배달중", cri));
        response.put("pageMaker", new PagenationDTO(cri, total));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/completed")
    public ResponseEntity<Object> completedDeliveryList(@RequestBody Criteria cri, @AuthenticationPrincipal UserDTO user) throws Exception {
        CrewDTO crew = crewService.getCrew(user.getUserId());

        Map<String, Object> response = new HashMap<>();
        int total = crewService.getDeliveryList(crew.getCrewId(), "배달완료").size();

        response.put("completedDeliveryList", crewService.getDeliveryListWithPaging(crew.getCrewId(), "배달완료", cri));
        response.put("pageMaker", new PagenationDTO(cri, total));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/collect/{rid}")
    public ResponseEntity<String> collect(@PathVariable Long rid, @AuthenticationPrincipal UserDTO user) throws  Exception {
        CrewDTO crew = crewService.getCrew(user.getUserId());
        crew.setUser(userService.getUserById(user.getUserId()));


        return rcService.insertReservationConfirmed(rid, crew) != null ? ResponseEntity.status(HttpStatus.OK).body("success")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");

    }

    @PostMapping("/process/{rid}/{wcid}")
    public ResponseEntity<String> process(@PathVariable Long rid, @PathVariable Long wcid,
                                            @AuthenticationPrincipal UserDTO user) throws Exception{

        CrewDTO crew = crewService.getCrew(user.getUserId());
        crew.setUser(userService.getUserById(user.getUserId()));

        WashingCompleteDTO washingCompleteDTO = new WashingCompleteDTO();
        washingCompleteDTO.setWcId(wcid);

        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setCrew(crew);
        deliveryDTO.setWc(washingCompleteDTO);

        return crewService.updateDelivering(rid, deliveryDTO) == true ? ResponseEntity.status(HttpStatus.OK).body("success")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        // String flag를 뻇음. front에서 redirect 시켜야할듯
    }

    @PostMapping("/completed/{rid}/{deliveryid}")
    public ResponseEntity<String> completed(@PathVariable Long rid, @PathVariable Long deliveryid) throws  Exception{
        return crewService.updateResDoneDelivery(rid, deliveryid) == true ? ResponseEntity.status(HttpStatus.OK).body("success")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        // String flag를 뻇음. front에서 redirect 시켜야할듯
    }





}
