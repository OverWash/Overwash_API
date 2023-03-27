package com.meta.overwash.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

	private Long reservationId;
	@DateTimeFormat(pattern = "yyyy-MM-dd" )
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date collectDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd" )
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date reservationDate;
	private String request;
	private String reservationStatus;
	private MemberDTO member;
}
