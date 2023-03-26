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

    @GetMapping("/pickup-list-limit")
    public List<ReservationDTO> pickupListLimit(@RequestBody Criteria cri) throws Exception {
        return crewService.getToBeCollectListWithPaging(cri);
    }

    @GetMapping("/to-be-limit")
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

    @GetMapping("/{userId}/modify")
    public CrewDTO modify(@PathVariable Long userId) throws Exception {
        CrewDTO crew = crewService.getCrew(userId);
        System.out.println(crew + " 22222222222222222222222222222222");
        return crew;        // 여기서 리턴을 못함
    }

    @PatchMapping("/modify") // 여기도 밑에처럼 @PathVariable 없애고 crew로 한번에 받음
    public ResponseEntity<String> modify(@RequestBody CrewDTO crew) throws Exception {
        return crewService.modify(crew.getUser(), crew) == true ? ResponseEntity.status(HttpStatus.OK).body("success")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
    }

    // 비밀번호 비교를 위해 user 객체가 필요함. @PathVariable과 @RequestBody로 따로 안받고 @RequestBody로 한번에 받음
    @PostMapping("/checkpw")
    public ResponseEntity<String> checkPw(@RequestBody UserDTO user) throws Exception {
        return crewService.checkPw(user) == true ? ResponseEntity.status(HttpStatus.OK).body("success")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
    }

    @PatchMapping("/{userId}/remove")
    public ResponseEntity<String> remove(@PathVariable Long userId) throws Exception {
        return userService.remove(userId) == true ? ResponseEntity.status(HttpStatus.OK).body("success")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
    }
}
