//package com.meta.overwash.controller.rest;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.meta.overwash.domain.Criteria;
//import com.meta.overwash.domain.ReservationConfirmedDTO;
//import com.meta.overwash.service.ReservationConfirmedService;
//
//@RestController
//public class ReservationConfirmedRestController {
//
//	@Autowired
//	ReservationConfirmedService rcService;
//
//	// 관리자 예약확정(검수예정) 리스트에 대한 컨트롤러
//	@GetMapping("/admin/confirmed")
//	public ResponseEntity<Map<String, Object>> reservationConfirmedList(Criteria cri) {
//		return new ResponseEntity<Map<String, Object>>(rcService.getList(cri), HttpStatus.OK);
//	}
//
//	// 결제완료 내역 출력
//	@GetMapping("/admin/rc/list")
//	public ResponseEntity<List<ReservationConfirmedDTO>> reservationConfirmedCompletePayment() {
//
//		return new ResponseEntity<List<ReservationConfirmedDTO>>(rcService.getListToPaymentCompleteList(),
//				HttpStatus.OK);
//	}
//}
