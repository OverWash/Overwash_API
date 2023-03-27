package com.meta.overwash.api;

import com.meta.overwash.domain.*;
import com.meta.overwash.service.CrewService;
import com.meta.overwash.service.ReservationConfirmedService;
import com.meta.overwash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crew")
public class CrewController {

    @Autowired
    private CrewService crewService;

    @Autowired
    private ReservationConfirmedService rcService;

    @Autowired
    private UserService userService;

    @PostMapping("/pickup-list-limit")
    public List<ReservationDTO> pickupListLimit(@RequestBody Criteria cri) throws Exception {
        return crewService.getToBeCollectListWithPaging(cri);
    }

    @PostMapping("/to-be-limit")
    public List<WashingCompleteDTO> toBeDeliveryListLimit(@RequestBody Criteria cri) throws Exception {
        return crewService.getWcListWithPaging(cri);
    }

    @GetMapping("/in-process-cnt")
    public int inProcessDeliveryCnt(@AuthenticationPrincipal UserDTO user) throws Exception{
        CrewDTO crew = crewService.getCrew(user.getUserId());
        return crewService.getDeliveryList(crew.getCrewId(), "배달중").size();
    }

    @GetMapping("/completed-cnt")
    public int completedDeliveryCnt(@AuthenticationPrincipal UserDTO user) throws Exception{
        CrewDTO crew = crewService.getCrew(user.getUserId());
        return crewService.getDeliveryList(crew.getCrewId(), "배달완료").size();
    }

    @GetMapping("/modify")
    public CrewDTO modify(@AuthenticationPrincipal UserDTO user) throws Exception {
        CrewDTO crew = crewService.getCrew(user.getUserId());
        crew.setUser(user);
        return crew;
    }

    @PatchMapping("/modify") // 여기도 밑에처럼 @PathVariable 없애고 crew로 한번에 받음
    public ResponseEntity<String> modify(@RequestBody CrewDTO crew) throws Exception {
        return crewService.modify(crew.getUser(), crew) == true ? ResponseEntity.status(HttpStatus.OK).body("success")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
    }

    @GetMapping("/crew-name")
    public String crewName(@AuthenticationPrincipal UserDTO user) throws Exception {
        return crewService.getCrewName(user.getUserId());
    }

    // 비밀번호 비교를 위해 user 객체가 필요함. @PathVariable과 @RequestBody로 따로 안받고 @RequestBody로 한번에 받음
    @PostMapping("/checkpw")
    public ResponseEntity<String> checkPw(@RequestBody UserDTO user) throws Exception {
        return crewService.checkPw(user) == true ? ResponseEntity.status(HttpStatus.OK).body("success")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
    }

    @PatchMapping("/remove")
    public ResponseEntity<String> remove(@AuthenticationPrincipal UserDTO user) throws Exception {
        return userService.remove(user.getUserId()) == true ? ResponseEntity.status(HttpStatus.OK).body("success")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
    }
}
