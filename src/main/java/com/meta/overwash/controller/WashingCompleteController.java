//package com.meta.overwash.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.meta.overwash.domain.ReservationConfirmedDTO;
//import com.meta.overwash.service.WashingCompleteService;
//
//@Controller
//public class WashingCompleteController {
//
//	@Autowired
//	private WashingCompleteService washingCompleteService;
//
//	@PostMapping(value = "/admin/complete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public String washingComplete(@RequestBody ReservationConfirmedDTO rcDto) {
//		washingCompleteService.insertWashingComplete(rcDto);
//
//		return "redirect:/admin/main";
//	}
//
//}