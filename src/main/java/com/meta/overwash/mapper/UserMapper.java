package com.meta.overwash.mapper;

import com.meta.overwash.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	public void insertUser(UserDTO user);

	public int deleteUser(Long userId);

	public int updateUser(UserDTO user);

	// for login security
	public UserDTO getUser(String email);

	public UserDTO getUserById(Long userId);
}
