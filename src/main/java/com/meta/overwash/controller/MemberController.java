//package com.meta.overwash.controller;
//
//import java.security.Principal;
//import java.text.ParseException;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.meta.overwash.domain.MemberDTO;
//import com.meta.overwash.domain.PaymentRequestDTO;
//import com.meta.overwash.domain.ReservationDTO;
//import com.meta.overwash.domain.UserDTO;
//import com.meta.overwash.service.MemberService;
//import com.meta.overwash.service.PaymentService;
//import com.meta.overwash.service.ReservationService;
//
//import lombok.extern.log4j.Log4j;
//
//@Controller
//@RequestMapping("/member/*")
//@EnableGlobalAuthentication
//public class MemberController {
//	@Autowired
//	private MemberService memberService;
//
//	@Autowired
//	private ReservationService reservationService;
//
//	@Autowired
//	PaymentService paymentService;
//
//	@GetMapping("/main")
//	public void main(HttpSession session, Principal principal, Model model) throws Exception {
//
//		// 메인페이지에서 보여줄 것들 추가
//		UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		MemberDTO member = memberService.getMember(user.getUserId());
//
//		session.setAttribute("username", member.getNickname()); // navBar에 닉네임 계속 보여 주기 위해
//		session.setAttribute("member", member);
//		Long usersId = user.getUserId();
//		List<ReservationDTO> reservations = reservationService.getListByMember(member.getMemberId());
//		List<PaymentRequestDTO> prList = paymentService.getPrListToMember(usersId);
//		if (reservations.size() > 0) {
//			int lastNum = reservations.size() - 1;
//			ReservationDTO lastReservation = reservations.get(lastNum);
//			model.addAttribute("reservations", reservations);
//			model.addAttribute("lastReservation", lastReservation);
//			model.addAttribute("prList", prList);
//		}
//
//	}
//
//	@PostMapping("/mypage")
//	public String myPage(HttpServletRequest request, Model model) throws Exception {
//		Long userId = Long.parseLong(request.getParameter("userId"));
//		model.addAttribute("memberDTO", memberService.getMember(userId));
//
//		return "member/mypage";
//	}
//
//	@PostMapping("/modifyInfo")
//	public String modifyInfo(HttpServletRequest request, Model model) throws Exception {
//		Long userId = Long.parseLong(request.getParameter("userId"));
//		model.addAttribute("memberDTO", memberService.getMember(userId));
//		return "member/modifyInfo";
//	}
//
//}
