//package com.meta.overwash.controller;
//
//
//import java.security.Principal;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.meta.overwash.domain.CrewDTO;
//import com.meta.overwash.domain.MemberDTO;
//import com.meta.overwash.domain.UserDTO;
//import com.meta.overwash.service.CrewService;
//import com.meta.overwash.service.LaundryService;
//import com.meta.overwash.service.MemberService;
//
//import lombok.extern.log4j.Log4j;
//
//@Controller
//@RequestMapping
//public class CommonController {
//
//	@Autowired
//	CrewService crewService;
//
//	@Autowired
//	MemberService memberService;
//
//	@Autowired
//	LaundryService laundryService;
//
//	// 처음 서버 켰을 때 루트 페이지가 로그인 페이지
//	@GetMapping("/")
//	public String start() {
//		return "redirect:/login";
//	}
//
//	@GetMapping("/login")
//	public String login(String error, String logout, Model model) {
//
//
//		// 로그인 실패 시 view로 에러 메시지 넘겨 줌
//		if (error != null) {
//			model.addAttribute("error", "로그인에 실패하였습니다.");
//		}
//
//		// 로그아웃했을 경우
//		if (logout != null) {
//			model.addAttribute("logout", "로그아웃 완료.");
//		}
//
//		return "login";
//	}
//
//	// 접근 권한 에러 커스텀 처리
////	@GetMapping("/accessError")
////	public void accessError(Authentication auth, Model model) {
////		model.addAttribute("msg", "해당 권한이 없습니다.");
////	}
//
//	@GetMapping("/register")
//	public void register() {
//	}
//
//	@PostMapping("/register")
//	public String register(String role) {
//		// forward 방식
//		return role.equals("ROLE_MEMBER") ? "/register/member" : "/register/crew";
//	}
//
//	@GetMapping({ "/register/member", "/register/crew" })
//	public void registerUser() {
//
//	}
//
//	@PostMapping("/register/member")
//	public String registerMember(UserDTO user, MemberDTO member) throws Exception {
//		// form에서 값 제대로 넘어왔는지 확인
//
//		memberService.insert(user, member);
//
//		return "redirect:/login";
//	}
//
//	@PostMapping("/register/crew")
//	public String registerCrew(UserDTO user, CrewDTO crew) throws Exception {
//		// form에서 값 제대로 넘어왔는지 확인
//
//		crewService.insert(user, crew);
//
//		return "redirect:/login";
//	}
//
//	@GetMapping("/info/pricelist")
//	public void priceList(Model model) {
//		model.addAttribute("laundrylist", laundryService.getList());
//	}
//
//	@GetMapping("/info/guide")
//	public void guide() {
//
//	}
//}
