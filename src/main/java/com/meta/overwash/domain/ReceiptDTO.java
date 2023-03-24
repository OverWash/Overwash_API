package com.meta.overwash.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReceiptDTO {
	private Long receiptId;
	private String paymentMethod;
	private PaymentRequestDTO pr;
}
