package com.meta.overwash.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meta.overwash.domain.*;
import com.meta.overwash.service.CrewService;
import com.meta.overwash.service.LaundryService;
import com.meta.overwash.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CommonRestController {

    @Autowired
    MemberService memberService;

    @Autowired
    CrewService crewService;

    @Autowired
    LaundryService laundryService;

    // 멤버 회원가입 등록
    @PostMapping("/register/member")
    public ResponseEntity<MemberDTO> registerMember(@RequestBody ObjectNode saveObj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();   // JSON 을  Object 로 매핑해주는 Jackson ObjectMapper 이용
        UserDTO user = mapper.treeToValue(saveObj.get("user"), UserDTO.class);
        MemberDTO member = mapper.treeToValue(saveObj.get("member"), MemberDTO.class);

        memberService.insert(user, member);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    // 크루 회원가입 등록
    @PostMapping("/register/crew")
    public ResponseEntity<CrewDTO> registerCrew(@RequestBody ObjectNode saveObj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();   // JSON 을  Object 로 매핑해주는 Jackson ObjectMapper 이용
        UserDTO user = mapper.treeToValue(saveObj.get("user"), UserDTO.class);
        CrewDTO crew = mapper.treeToValue(saveObj.get("crew"), CrewDTO.class);

        crewService.insert(user, crew);
        return ResponseEntity.status(HttpStatus.CREATED).body(crew);
    }

//    // 가격리스트
//    @GetMapping("/info/price")
//    public List<LaundryDTO> priceList() {
//        return laundryService.getList();
//    }

    // 가격리스트 페이징 처리
    @GetMapping("/info/price")
    public ResponseEntity<Map<String, Object>> priceListPaging(Criteria cri,
                                                               @RequestParam(required = false, defaultValue = "1") int page,
                                                               @RequestParam(required = false, defaultValue = "10") int amount) throws Exception {
		cri.setPageNum(page);
		cri.setAmount(amount);
		return new ResponseEntity<Map<String, Object>>(laundryService.getListPaging(cri), HttpStatus.OK);
	}
}
