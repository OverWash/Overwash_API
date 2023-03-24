//package com.meta.overwash.controller.rest;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.meta.overwash.domain.CheckDTO;
//import com.meta.overwash.service.CheckService;
//
//@RestController
//public class CheckRestController {
//
//	@Autowired
//	CheckService checkService;
//
//
//	// 검수 리스트를 json 으로 전송
//	@GetMapping(value = "/check/{confirmId}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<CheckDTO>> getCheckList(@PathVariable("confirmId") Long confirmId) {
//		return new ResponseEntity<List<CheckDTO>>(checkService.getCheckList(confirmId),HttpStatus.OK);
//	}
//}
