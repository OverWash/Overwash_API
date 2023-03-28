package com.meta.overwash.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meta.overwash.domain.MemberDTO;
import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.mapper.MemberMapper;
import com.meta.overwash.mapper.UserMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	@Transactional
	public void insert(UserDTO user, MemberDTO member) throws Exception {

//		user.setRole("ROLE_MEMBER");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		// user 에 먼저 로그인 정보 insert
		userMapper.insertUser(user);

		// user를 담은 member를 insert
		member.setUser(user);
		memberMapper.insertMember(member);

	}

	@Override
	public boolean remove(Long memberId) throws Exception {
		return memberMapper.deleteMember(memberId) == 1;
	}

	@Override
	@Transactional
	public boolean modify(UserDTO user, MemberDTO member) throws Exception {
		System.out.println(user.getPassword().length());
		if (user.getPassword() != null && user.getPassword().length() != 0) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			if (userMapper.updateUser(user) == 0)
				return false;
		}

		return memberMapper.updateMember(member) == 1;

	}

	@Override
	public MemberDTO getMember(Long userId) throws Exception {
		return memberMapper.getMember(userId);
	}

	@Override
	public List<MemberDTO> getMemberList(String role) throws Exception {
		return memberMapper.getMemberList(role);
	}

	@Override
	public boolean checkPw(UserDTO user) throws Exception {
		UserDTO userInfo = userMapper.getUser(user.getEmail());

		if (userInfo != null) {
			if (bCryptPasswordEncoder.matches(user.getPassword(), userInfo.getPassword())) {
				return true;
			}
		}
		return false;

	}

	@Override
	public String getContact(String contact) throws Exception {
		return memberMapper.getMemberContact(contact);
	}

}
