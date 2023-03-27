package com.meta.overwash.api;

import com.meta.overwash.domain.ReceiptDTO;
import com.meta.overwash.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment/*")
public class PaymentRestController {

    @Autowired
    PaymentService paymentService;

    // 고객의 결제진행(완료) 시 영수증 발급
	@PostMapping("/process/{prId}")
	public ResponseEntity<ReceiptDTO> processRequest(@PathVariable Long prId, @RequestBody ReceiptDTO receipt) {
		paymentService.paymentProcess(prId, receipt);
        return ResponseEntity.status(HttpStatus.CREATED).body(receipt);
	}
}
