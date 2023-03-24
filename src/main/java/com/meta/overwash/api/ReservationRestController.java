package com.meta.overwash.api;

import com.meta.overwash.domain.MemberDTO;
import com.meta.overwash.domain.ReservationDTO;
import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.service.MemberService;
import com.meta.overwash.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/reservations/")
@RestController
public class ReservationRestController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private MemberService memberService;


//    @GetMapping(value = "{userId}", produces = "application/hal+json; charset=UTF-8")
//    public String getListByMember(@PathVariable("userId") Long userId) throws Exception {
//
//        UserDTO user = new UserDTO();
//        user.setUserId(userId);
//        MemberDTO member = memberService.getMember(user.getUserId());
//        List<ReservationDTO> reservations = reservationService.getListByMember(member.getMemberId());
//        String json = new Gson().toJson(reservations);
//        return json;
//    }

	@GetMapping(value = "{userId}", produces = "application/hal+json; charset=UTF-8")
	public ResponseEntity<List<ReservationDTO>> getListByMember(@PathVariable("userId") Long userId) throws Exception {

          UserDTO user = new UserDTO();
          user.setUserId(userId);
          MemberDTO member = memberService.getMember(user.getUserId());
          List<ReservationDTO> reservations = reservationService.getListByMember(member.getMemberId());

		  return new ResponseEntity<>(reservations, HttpStatus.OK);

	}

    @DeleteMapping("{reservationId}")
    public void deleteReservation(@PathVariable Long reservationId){
        reservationService.removeReservation(reservationId);
    }




}
