package com.meta.overwash.api;

import com.meta.overwash.domain.MemberDTO;
import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.service.MemberService;
import com.meta.overwash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserService userService;

    @GetMapping("/modify")
    public MemberDTO modify(@AuthenticationPrincipal UserDTO user) throws Exception {
        MemberDTO member = memberService.getMember(user.getUserId());
        member.setUser(user);
        return member;
    }

    @PatchMapping("/modify") // 여기도 밑에처럼 @PathVariable 없애고 member로 한번에 받음
    public ResponseEntity<String> modify(@RequestBody MemberDTO member) throws Exception {
        return memberService.modify(member.getUser(), member) == true ? ResponseEntity.status(HttpStatus.OK).body("success")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
    }

    // 비밀번호 비교를 위해 user 객체가 필요함. @PathVariable과 @RequestBody로 따로 안받고 @RequestBody로 한번에 받음
    @PostMapping("/checkpw")
    public ResponseEntity<String> checkPw(@RequestBody UserDTO user) throws Exception {
        return memberService.checkPw(user) == true ? ResponseEntity.status(HttpStatus.OK).body("success")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
    }

    @PatchMapping("/remove")
    public ResponseEntity<String> remove(@AuthenticationPrincipal UserDTO user) throws Exception {
        return userService.remove(user.getUserId()) == true ? ResponseEntity.status(HttpStatus.OK).body("success")
                    : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
    }


}
