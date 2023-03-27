package com.meta.overwash.api;

import com.meta.overwash.domain.Criteria;
import com.meta.overwash.domain.ReceiptDTO;
import com.meta.overwash.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment/*")
public class PaymentRestController {

    @Autowired
    PaymentService paymentService;

    // 고객의 결제진행(완료) 시 영수증 발급
	@PostMapping("/process/{prId}")
	public ResponseEntity<ReceiptDTO> processRequest(@PathVariable Long prId, @RequestBody ReceiptDTO receipt) {
		paymentService.paymentProcess(prId, receipt);
        return ResponseEntity.status(HttpStatus.CREATED).body(receipt);
	}

    // 고객의 영수증 리스트 페이징
    @GetMapping("/receipt-list/{userId}")
    public ResponseEntity<Map<String, Object>> receiptList(@PathVariable Long userId,
                                                           @RequestParam(required = false, defaultValue = "1") int page,
                                                           @RequestParam(required = false, defaultValue = "10") int amount,
                                                           Criteria cri) {
        cri.setPageNum(page);
        cri.setAmount(amount);
        return new ResponseEntity<Map<String, Object>>(paymentService.getReceiptListPaging(cri, userId), HttpStatus.OK);
    }

    // 고객의 영수증 하나 (디테일)
    @GetMapping("/receipt/{rId}")
    public ResponseEntity<ReceiptDTO> receiptDetail(@PathVariable Long rId) {
        ReceiptDTO receipt = paymentService.getReceipt(rId);
        return ResponseEntity.status(HttpStatus.OK).body(receipt);
    }
 }
