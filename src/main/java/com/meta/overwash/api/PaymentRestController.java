package com.meta.overwash.api;

import com.meta.overwash.domain.PaymentRequestDTO;
import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/payments/")
@RestController
public class PaymentRestController {

    @Autowired
    PaymentService paymentService;

    @GetMapping(value = "{userId}", produces = "application/hal+json; charset=UTF-8")
    public ResponseEntity<List<PaymentRequestDTO>> GetPaymentRequestList(
        @PathVariable("userId") Long userId ) throws Exception {
        System.out.println("PrController GetPrList GetUser..... :" + userId);
        List<PaymentRequestDTO> prList = paymentService.getPrListToMember(userId);
        return new ResponseEntity<>(prList, HttpStatus.OK);
    }

    //	// 결제요청 리스트 불러오기
//	@GetMapping("/requestlist")
//	public void getRequestList(Principal principal, Model model) {
//		UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 현재 로그인 유저의 객체를 가져옴
//		model.addAttribute("prList", paymentService.getPrListToMember(user.getUserId()));
//	}
//
//	// 고객의 결제진행(완료) 시 영수증 발급
//	// rest로 변경?
//	@PostMapping("/process")
//	public String processRequest(Long prId, Long confirmId, ReceiptDTO receipt) {
//		paymentService.paymentProcess(prId, confirmId, receipt);
//		return "redirect:/member/main";
//	}
}
