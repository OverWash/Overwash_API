package com.meta.overwash.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CrewDTO {

	private Long crewId;
	private UserDTO user;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date crewBirth;

	private String crewContact;
	private String crewName;
	private String carType;
	private String carNumber;
}
