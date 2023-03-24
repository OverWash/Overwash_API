//package com.meta.overwash.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.meta.overwash.domain.LaundryDTO;
//import com.meta.overwash.service.PaymentService;
//
//@Controller
//public class PaymentRequestController {
//
//	@Autowired
//	PaymentService paymentService;
//
//	// 고객의 상세페이지
//	@GetMapping("/member/process/{pno}")
//	public String process(@PathVariable("pno") Long pno, Model model) {
//		model.addAttribute("payment", paymentService.get(pno));
//
//		return "redirect:/member/main";
//	}
//
//	// 결제 요청
//	// 수정중 (03.12) 세탁물 안받아와짐
//	@PostMapping(value = "/admin/payment/request/{rcNo}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public String adminPaymentRequest(@RequestBody List<LaundryDTO> laundryList, @PathVariable("rcNo") Long rcNo, Model model) {
//		if (paymentService.requestToAdmin(rcNo, laundryList) != null) {
//			model.addAttribute("msg", "결제요청이 완료되었습니다.");
//
//		} else {
//			model.addAttribute("msg", "결제 요청이 취소되었습니다.");
//		}
//		return "/admin/main";
//	}
//}
