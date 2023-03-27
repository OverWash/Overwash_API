package com.meta.overwash.api;

import com.meta.overwash.domain.MemberDTO;
import com.meta.overwash.domain.ReservationDTO;
import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.domain.Criteria;
import com.meta.overwash.domain.MemberDTO;
import com.meta.overwash.domain.ReservationDTO;
import com.meta.overwash.service.MemberService;
import com.meta.overwash.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@RequestMapping("/reservations/")
@RestController
//@CrossOrigin(origins = "*")
public class ReservationRestController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private MemberService memberService;


    @GetMapping(value = "{userId}", produces = "application/hal+json; charset=UTF-8")
    public ResponseEntity<List<ReservationDTO>> getListByMember(@PathVariable("userId") Long userId
            //@AuthenticationPrincipal UserDTO user
    ) throws Exception {
        UserDTO user = new UserDTO();
        user.setUserId(userId);
        MemberDTO member = memberService.getMember(user.getUserId());
        List<ReservationDTO> reservations = reservationService.getListByMember(member.getMemberId());

        return new ResponseEntity<>(reservations, HttpStatus.OK);

    }

    @PostMapping(value="{userId}", produces = "application/hal+json; charset=UTF-8")
    public void registerReservation(@PathVariable("userId") Long userId,
                                    @RequestBody ReservationDTO reservation) throws Exception{
        UserDTO user = new UserDTO();
        user.setUserId(userId);
        MemberDTO member = memberService.getMember(user.getUserId());
        reservation.setMember(member);
        reservationService.insert(reservation);
    }


    @DeleteMapping(value="{reservationId}", produces = "application/hal+json; charset=UTF-8")
    public ResponseEntity<String> removeReservation(
            @PathVariable("reservationId") Long reservationId) {
        return reservationService.removeReservation(reservationId)
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PATCH, value = "{reservationId}")
    public ResponseEntity<String> updateReservationRequest(
            @PathVariable("reservationId") Long reservationId,
            @RequestBody ReservationDTO reservation) {

        reservation.setReservationId(reservationId);

        return reservationService.updateReservationRequest(reservation)
            ? new ResponseEntity<>("success", HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Auth 가 왜 안될까?
    //    @GetMapping("/completed")
//    public List<DeliveryDTO> completedDeliveryList(@AuthenticationPrincipal UserDTO user) throws Exception {
//        CrewDTO crew = crewService.getCrew(user.getUserId());
//
//        return crewService.getDeliveryList(crew.getCrewId(), "배달완료");
//    }
//    @GetMapping("/reservations/{userid}")
//    public List<ReservationDTO> getReservationList(@PathVariable long userid) throws Exception {
//        MemberDTO member = memberService.getMember(userid);
//        return reservationService.getListByMember(member.getMemberId());
//    }

    @GetMapping("/reservations/{userid}")
    public ResponseEntity<Map<String, Object>> reservationList(Criteria cri, @PathVariable long userid,
                                                               @RequestParam(required = false, defaultValue = "1") int page,
                                                               @RequestParam(required = false, defaultValue = "10") int amount) throws Exception {
        cri.setPageNum(page);
        cri.setAmount(amount);
        MemberDTO member = memberService.getMember(userid);
        return new ResponseEntity<>(reservationService.getListByMember(cri, member.getMemberId()), HttpStatus.OK);
    }

}
