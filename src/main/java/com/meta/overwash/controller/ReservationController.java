//package com.meta.overwash.controller;
//
//import java.text.ParseException;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.meta.overwash.domain.MemberDTO;
//import com.meta.overwash.domain.ReservationDTO;
//import com.meta.overwash.service.ReservationService;
//
//import lombok.extern.log4j.Log4j;
//
//@Controller
//@RequestMapping("/reservation/*")
//public class ReservationController {
//
//	@Autowired
//	private ReservationService reservationService;
//
//	@GetMapping("/list")
//	public void getResevationList(HttpSession session, Model model) {
//		MemberDTO member = (MemberDTO) session.getAttribute("member");
//		model.addAttribute("reservationList", reservationService.getListByMember(member.getMemberId()));
//	}
//
//	@PostMapping("/request")
//	public String result(ReservationDTO reservation, HttpSession session) throws ParseException {
//		MemberDTO member = (MemberDTO) session.getAttribute("member");
//		reservation.setMember(member);
//		reservationService.insert(reservation);
//
//		return "redirect:/member/main";
//	}
//}
