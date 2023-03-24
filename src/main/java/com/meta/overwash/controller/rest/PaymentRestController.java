//package com.meta.overwash.controller.rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.meta.overwash.domain.ReceiptDTO;
//import com.meta.overwash.service.PaymentService;
//
//import lombok.extern.log4j.Log4j;
//
//@RestController
//@RequestMapping("/payment") // 시큐리티에 url 권한 나중에 추가해야 함
//public class PaymentRestController {
//
//	@Autowired
//	PaymentService paymentService;
//
//	@GetMapping(value = "/receipt/{rid}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ReceiptDTO getReceipt(@PathVariable("rid") Long reservationId) {
//		return paymentService.getReceipt(reservationId);
//	}
//
////	@GetMapping("/receipts")
////	public List<ReceiptDTO> getReceiptList() {
////		return paymentService.getReceiptList();
////	}
//
//
//}
