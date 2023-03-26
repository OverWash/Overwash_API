package com.meta.overwash.api;

import com.meta.overwash.domain.Criteria;
import com.meta.overwash.domain.MemberDTO;
import com.meta.overwash.domain.ReservationDTO;
import com.meta.overwash.service.MemberService;
import com.meta.overwash.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ReservationRestController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    MemberService memberService;

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
