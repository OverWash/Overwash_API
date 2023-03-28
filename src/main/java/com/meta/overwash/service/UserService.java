package com.meta.overwash.service;

import com.meta.overwash.domain.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public UserDTO getUser(String email);

    public UserDTO getUserById(Long userId);

    public boolean remove(Long userId);



}