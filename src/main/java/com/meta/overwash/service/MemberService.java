package com.meta.overwash.service;

import java.util.List;

import com.meta.overwash.domain.MemberDTO;
import com.meta.overwash.domain.UserDTO;

public interface MemberService {

	public void insert(UserDTO user, MemberDTO member) throws Exception;

	public boolean remove(Long memberId) throws Exception;

	public boolean modify(UserDTO user, MemberDTO member) throws Exception;

	public MemberDTO getMember(Long userId) throws Exception;

	public List<MemberDTO> getMemberList(String role) throws Exception;

	public boolean checkPw(UserDTO user) throws Exception;

	public String getContact(String contact) throws Exception;

}
