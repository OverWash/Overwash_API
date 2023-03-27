package com.meta.overwash.api;

import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.service.CrewService;
import com.meta.overwash.service.MemberService;
import com.meta.overwash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CrewService crewService;

    // 이메일 존재 여부 검사
    @GetMapping("/check")
    public String userEmail(@RequestParam("email") String email) {
        System.out.println("getUserEmail : 이메일 중복 검사 호출..........");

        /* email(username) 중복 체크 :  null이 아니면 이미 등록된 이메일이 있는 것 */
        return userService.getUser(email) == null ? "possible" : "impossible";
    }

    // 연락처 존재 여부 검사
    @GetMapping("/check/member/{contact}")
    public String memberContact(@PathVariable("contact") String contact) throws Exception {
        System.out.println("getMemberContact :  멤버 테이블 전화번호 중복 검사 호출...........");
        return memberService.getContact(contact) == null ? "possible" : "impossible";
    }

    @GetMapping("/check/crew/{contact}")
    public String crewContact(@PathVariable("contact") String contact) throws Exception {
        System.out.println("getCrewContact :  크루 테이블 전화번호 중복 검사 호출...........");
        return crewService.getContact(contact) == null ? "possible" : "impossible";
    }

}
