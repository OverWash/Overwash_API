package com.meta.overwash.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meta.overwash.domain.MemberDTO;
import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.service.CrewService;
import com.meta.overwash.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonRestController {

    @Autowired
    MemberService memberService;

    @Autowired
    CrewService crewService;

    @PostMapping("/register/member")
    public ResponseEntity<UserDTO> registerMember(@RequestBody ObjectNode saveObj) throws Exception {
        System.out.println("REGISTER CONTROLLER!!!!!!!");

        ObjectMapper mapper = new ObjectMapper();   // JSON 을  Object 로 매핑해주는 Jackson ObjectMapper 이용
        UserDTO user = mapper.treeToValue(saveObj.get("user"), UserDTO.class);
        MemberDTO member = mapper.treeToValue(saveObj.get("member"), MemberDTO.class);

        memberService.insert(user, member);

        System.out.println( "REQUEST BODY!!!!!!!! " + saveObj);
        System.out.println(user);
        System.out.println(member);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


}
