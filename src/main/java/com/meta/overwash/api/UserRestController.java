package com.meta.overwash.api;

import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    private UserService userService;
//    @PostMapping("/users")
//    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO requestUser){
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        UserDTO userDto = mapper.map(requestUser,UserDTO.class);
//        UserDTO createdUserDto = userService.createUser(userDto);
//        ResponseUser responseUser = mapper.map(createdUserDto, ResponseUser.class);
//
//        return new ResponseEntity<>(responseUser , HttpStatus.CREATED);
//
//    }
    @GetMapping("/test")
    public String test(@AuthenticationPrincipal UserDTO user){
//        System.out.println(user);
        return "test";
    }

}
