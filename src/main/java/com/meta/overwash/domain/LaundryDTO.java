package com.meta.overwash.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LaundryDTO {
	private Long laundryId;
	private String name;
	private String type;
	private LaundryPriceDTO laundryPrice;

}
