package com.meta.overwash.api;

import com.meta.overwash.domain.Criteria;
import com.meta.overwash.domain.LaundryDTO;
import com.meta.overwash.domain.PaymentRequestDTO;
import com.meta.overwash.domain.ReservationConfirmedDTO;
import com.meta.overwash.service.PaymentService;
import com.meta.overwash.service.ReservationConfirmedService;
import com.meta.overwash.service.WashingCompleteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    ReservationConfirmedService reservationConfirmedService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    WashingCompleteService washingCompleteService;

    //예약확정 내역 조회 ( Admin Main Page & Detail Page)
    @PostMapping("/confirmed")
    public ResponseEntity<Map<String, Object>> getReservationConfirmeds(@RequestBody Criteria cri) {

         Map<String,Object> maps = reservationConfirmedService.getList(cri);
        return new ResponseEntity<>(maps,HttpStatus.OK);
    }

    // 결제완료 내역 조회 (Admin Main Page & Detail Page)
    // 세탁완료 처리하기 위한 것
    @PostMapping("/rc/list")
    public ResponseEntity<Map<String, Object>> getPaymentRequests(@RequestBody Criteria cri) {
        Map<String, Object> maps = reservationConfirmedService.getListToPaymentCompleteList(cri);
        return new ResponseEntity<>(maps,HttpStatus.OK);
    }



    // 검수완료 처리하기
    @PostMapping("/rc/{confirmId}/complete")
    public ResponseEntity checkLaundryComplete(@PathVariable Long confirmId,
                                        @RequestBody List<LaundryDTO> laundryList){
        PaymentRequestDTO paymentRequest = paymentService.requestToAdmin(confirmId, laundryList);
        return new ResponseEntity(paymentRequest,HttpStatus.CREATED);
    }

    //세탁완료 처리하기
    @PostMapping("/wc/{confirmId}/complete")
    public ResponseEntity checkWashingComplete(@PathVariable Long confirmId){
        washingCompleteService.insertWashingComplete(confirmId);
    return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @GetMapping("/rc/{confirmId}/detail")
    public ResponseEntity<ReservationConfirmedDTO> checkConfirmDetail(@PathVariable Long confirmId){
        return new ResponseEntity<>(reservationConfirmedService.findById(confirmId), HttpStatus.OK);
    }
}

