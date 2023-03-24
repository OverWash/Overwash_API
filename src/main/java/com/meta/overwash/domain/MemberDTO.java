package com.meta.overwash.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDTO {

	private Long memberId;
	private UserDTO user;
	private String memberAddress;
	private String memberContact;
	private String nickname;
}
