//package com.meta.overwash.controller.rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import com.meta.overwash.domain.MemberDTO;
//import com.meta.overwash.domain.ReservationDTO;
//import com.meta.overwash.domain.UserDTO;
//import com.meta.overwash.service.ReservationService;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.log4j.Log4j;
//
//@RequestMapping("/reservations/*")
//@RestController
//@AllArgsConstructor
//public class ReservationRestController {
//
//	@Autowired
//	ReservationService reservationService;
//
//	@RequestMapping(value = "/{username}/{reservationId}",
//			produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
//			method=RequestMethod.GET)
//	public ResponseEntity<ReservationDTO> reservationDetail(
//			@PathVariable("username") String email,
//			@PathVariable("reservationId") Long reservationId) {
//		UserDTO user = new UserDTO();
//		user.setEmail(email);
//		MemberDTO member = new MemberDTO();
//		member.setUser(user);
//		ReservationDTO reservation = new ReservationDTO();
//		reservation.setMember(member);
//		reservation.setReservationId(reservationId);
//		return new ResponseEntity<>(reservationService.getListEachOne(reservation), HttpStatus.OK);
//
//	}
//
//}
