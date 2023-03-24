//package com.meta.overwash.controller.rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.meta.overwash.domain.MemberDTO;
//import com.meta.overwash.domain.UserDTO;
//import com.meta.overwash.service.MemberService;
//
//@RestController
//@RequestMapping("/member")
//public class MemberRestController {
//
//	@Autowired
//	private MemberService memberService;
//
//	@PatchMapping("/modify")
////	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PATCH, value = "modify")
//	public ResponseEntity<String> modify(@RequestBody MemberDTO member) throws Exception {
//		return memberService.modify(member.getUser(), member) == true
//				? new ResponseEntity<String>("success", HttpStatus.OK)
//				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@PatchMapping("/remove/{memberId}")
//	public ResponseEntity<String> remove(@PathVariable("memberId") Long memberId) throws Exception {
//		return memberService.remove(memberId) == true ? new ResponseEntity<String>("success", HttpStatus.OK)
//				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//
//	}
//
//	@PostMapping("/checkpw")
//	public String checkpw(@RequestBody UserDTO user) throws Exception{
//		if (memberService.checkPw(user)) {
//			return "success";
//		}
//		return "fail";
//	}
//}
