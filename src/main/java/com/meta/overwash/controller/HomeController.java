//package com.meta.overwash.controller;
//
//import java.util.List;
//import java.util.Locale;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.meta.overwash.domain.MemberDTO;
//import com.meta.overwash.mapper.MemberMapper;
//
///**
// * Handles requests for the application home page.
// */
//@Controller
//public class HomeController {
//	@Autowired
//	MemberMapper membermap;
//
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//
//	/**
//	 * Simply selects the home view to render by returning its name.
//	 */
////	@RequestMapping(value = "/", method = RequestMethod.GET)
////	public String home(Locale locale, Model model) {
//////		List<MemberDTO> members = membermap.getList();
//////		model.addAttribute("members", members);
////		System.out.println("SERVER ON");
////		return "home";
////	}
//
//	@RequestMapping(value = "/test", method = RequestMethod.GET)
//	public String test() {
//		return "sign";
//	}
//
//}
