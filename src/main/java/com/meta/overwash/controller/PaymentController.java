//package com.meta.overwash.controller;
//
//import java.security.Principal;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.meta.overwash.domain.ReceiptDTO;
//import com.meta.overwash.domain.UserDTO;
//import com.meta.overwash.service.MemberService;
//import com.meta.overwash.service.PaymentService;
//
//import lombok.extern.log4j.Log4j;
//
//@Controller
//@RequestMapping("/payment")
//public class PaymentController {
//	@Autowired
//	PaymentService paymentService;
//
//	@Autowired
//	MemberService memberService;
//
//
//	// 결제요청 리스트 불러오기
//	@GetMapping("/requestlist")
//	public void getRequestList(Principal principal, Model model) {
//		UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 현재 로그인 유저의 객체를 가져옴
//		model.addAttribute("prList", paymentService.getPrListToMember(user.getUserId()));
//	}
//
//	// 고객의 결제진행(완료) 시 영수증 발급
//	// rest로 변경?
//	@PostMapping("/process")
//	public String processRequest(Long prId, Long confirmId, ReceiptDTO receipt) {
//		paymentService.paymentProcess(prId, confirmId, receipt);
//		return "redirect:/member/main";
//	}
//
//	// view
//	// 멤버 권한
//	@GetMapping("/receipt")
//	public void getReceipt() {
//
//	}
//
//	// 멤버 권한
//	// 어떤 멤버에 대한 영수증 목록인지
//	@GetMapping("/receiptlist")
//	public void getReceiptList(Principal principal, Model model) throws Exception {
//		UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 현재 로그인 유저의 객체를 가져옴
//		model.addAttribute("receipts", paymentService.getReceiptList(user.getUserId()));
//	}
//
//	@GetMapping("/completedlist")
//	public void getDeliveryCompletedList(Principal principal, Model model) {
//		UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 현재 로그인 유저의 객체를 가져옴
//		model.addAttribute("completedList", paymentService.getDeliveryCompletedList(user.getUserId()));
//	}
//
//}
