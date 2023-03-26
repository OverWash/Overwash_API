package com.meta.overwash.api;

import com.meta.overwash.domain.Criteria;
import com.meta.overwash.domain.PaymentRequestDTO;
import com.meta.overwash.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment/*")
public class PaymentRequestRestController {

    @Autowired
    PaymentService paymentService;

    // 고객이 조회하는 결제리스트
	@GetMapping("/request-list/{userId}")
	public ResponseEntity<Map<String, Object>> paymentListToMember(Criteria cri, @PathVariable Long userId,
																   @RequestParam(required = false, defaultValue = "1") int page,
																   @RequestParam(required = false, defaultValue = "10") int amount) {
		cri.setPageNum(page);
		cri.setAmount(amount);
		return new ResponseEntity<Map<String, Object>>(paymentService.getListToMember(cri, userId), HttpStatus.OK);
	}

	// prid로 가져오는 결제요청서 상세내역
	@GetMapping("/request/{prId}")
	public ResponseEntity<PaymentRequestDTO> paymentRequest(@PathVariable Long prId) {
		return new ResponseEntity<>(paymentService.get(prId), HttpStatus.OK);
	}
}
