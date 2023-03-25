package com.meta.overwash.api;

import com.meta.overwash.domain.MemberDTO;
import com.meta.overwash.domain.ReservationDTO;
import com.meta.overwash.service.MemberService;
import com.meta.overwash.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationRestController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    MemberService memberService;

    @GetMapping("/reservations/{userid}")
    public List<ReservationDTO> getReservationList(@PathVariable long userid) throws Exception {
        MemberDTO member = memberService.getMember(userid);
        return reservationService.getListByMember(member.getMemberId());
    }

}
