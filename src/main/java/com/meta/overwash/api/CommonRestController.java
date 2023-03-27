package com.meta.overwash.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meta.overwash.domain.CrewDTO;
import com.meta.overwash.domain.LaundryDTO;
import com.meta.overwash.domain.MemberDTO;
import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.service.CrewService;
import com.meta.overwash.service.LaundryService;
import com.meta.overwash.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommonRestController {

    @Autowired
    MemberService memberService;

    @Autowired
    CrewService crewService;

    @Autowired
    LaundryService laundryService;

    @PostMapping("/register/member")
    public ResponseEntity<MemberDTO> registerMember(@RequestBody ObjectNode saveObj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();   // JSON 을  Object 로 매핑해주는 Jackson ObjectMapper 이용
        UserDTO user = mapper.treeToValue(saveObj.get("user"), UserDTO.class);
        MemberDTO member = mapper.treeToValue(saveObj.get("member"), MemberDTO.class);

        memberService.insert(user, member);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @PostMapping("/register/crew")
    public ResponseEntity<CrewDTO> registerCrew(@RequestBody ObjectNode saveObj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();   // JSON 을  Object 로 매핑해주는 Jackson ObjectMapper 이용
        UserDTO user = mapper.treeToValue(saveObj.get("user"), UserDTO.class);
        CrewDTO crew = mapper.treeToValue(saveObj.get("crew"), CrewDTO.class);

        crewService.insert(user, crew);
        return ResponseEntity.status(HttpStatus.CREATED).body(crew);
    }

    // 가격리스트
    @GetMapping("/info/price")
    public List<LaundryDTO> priceList() {
        return laundryService.getList();
    }

}
