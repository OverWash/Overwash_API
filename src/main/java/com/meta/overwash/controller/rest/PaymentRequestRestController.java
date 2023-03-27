//package com.meta.overwash.controller.rest;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.meta.overwash.domain.Criteria;
//import com.meta.overwash.service.PaymentService;
//
//@RestController
//public class PaymentRequestRestController {
//
//	@Autowired
//	PaymentService paymentService;
//
//	// 관리자가 요청하는 결제리스트
//	@GetMapping(value="/admin/payment")
//
//	public ResponseEntity<Map<String, Object>> paymentListToAdmin(Criteria cri) {
//
//		return new ResponseEntity<Map<String, Object>>(paymentService.getListToAdmin(cri), HttpStatus.OK);
//	}
//
//	// 고객이 요청하는 결제리스트
//	@GetMapping("/member/payment")
//	public ResponseEntity<Map<String, Object>> paymentListToMember(Criteria cri, Long userId) {
//
//		return new ResponseEntity<Map<String, Object>>(paymentService.getListToMember(cri, userId), HttpStatus.OK);
//
//	}
//
//}
