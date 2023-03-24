package com.meta.overwash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userMapper.getUser(username);
        return user;
    }

    @Override
    public UserDTO getUser(String email) {
        return userMapper.getUser(email);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return userMapper.getUserById(userId);
    }

}