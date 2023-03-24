package com.meta.overwash.mapper;

import java.util.List;

import com.meta.overwash.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

	public void insertMember(MemberDTO member) throws Exception;

	public List<MemberDTO> getMemberList(String role) throws Exception;

	public MemberDTO getMember(Long userId) throws Exception;

	public int updateMember(MemberDTO member) throws Exception;

	public int deleteMember(Long memberId) throws Exception;

	public String getMemberContact(String contact) throws Exception;

}
