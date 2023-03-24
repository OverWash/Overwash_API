//package com.meta.overwash.controller.rest;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.meta.overwash.service.CrewService;
//import com.meta.overwash.service.MemberService;
//import com.meta.overwash.service.UserService;
//
//import lombok.extern.log4j.Log4j;
//
//@RestController
//public class CommonRestController {
//
//	@Autowired
//	private UserService userSerivce;
//
//	@Autowired
//	private MemberService memberService;
//
//	@Autowired
//	private CrewService crewService;
//
//	@GetMapping("/check")
//	public String getUserEmail(@RequestParam("email") String email) {
//
//		/* email(username) 중복 체크 :  null이 아니면 이미 등록된 이메일이 있는 것 */
//		return userSerivce.getUser(email) == null ? "possible" : "impossible";
//	}
//
//	@GetMapping("/check/member/{contact}")
//	public String getMemberContact(@PathVariable("contact") String contact) throws Exception {
//		return memberService.getContact(contact) == null ? "possible" : "impossible";
//	}
//
//	@GetMapping("/check/crew/{contact}")
//	public String getCrewContact(@PathVariable("contact") String contact) throws Exception {
//		return crewService.getContact(contact) == null ? "possible" : "impossible";
//	}
//
//
//
//}
