//package com.meta.overwash.controller.rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.meta.overwash.domain.CrewDTO;
//import com.meta.overwash.domain.MemberDTO;
//import com.meta.overwash.domain.ReservationConfirmedDTO;
//import com.meta.overwash.domain.UserDTO;
//import com.meta.overwash.service.CrewService;
//
//@RestController
//@RequestMapping("/crew")
//public class CrewRestController {
//
//	@Autowired
//	private CrewService crewService;
//
//	@PatchMapping("/modify")
//	public ResponseEntity<String> modify(@RequestBody CrewDTO crew) throws Exception {
//		return crewService.modify(crew.getUser(), crew) == true ? new ResponseEntity<String>("success", HttpStatus.OK)
//				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@PatchMapping("/remove/{crewId}")
//	public ResponseEntity<String> remove(@PathVariable("crewId") Long crewId) throws Exception {
//
//		return crewService.remove(crewId) == true ? new ResponseEntity<String>("success", HttpStatus.OK)
//				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//
//	}
//
//	@PostMapping("/checkpw")
//	public String checkpw(@RequestBody UserDTO user) throws Exception {
//		if (crewService.checkPw(user)) {
//			return "success";
//		}
//		return "fail";
//	}
//
//}
