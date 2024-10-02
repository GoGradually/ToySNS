package toysns.toysns.domain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @PostMapping("/member/create")
    public String createMember(){
        return null;
    }

    @PostMapping("/member/{id}")
    public String updateMember(){
        return null;
    }

    @GetMapping("/member/{id}")
    public String findMemberById(){
        return null;
    }

    @GetMapping("/members")
    public String findMemberByUsername(){
        return null;
    }

    @DeleteMapping("/member/{id}")
    public String deleteMemberById(){
        return null;
    }

    @PostMapping("/member/{id}/deactivate")
    public String deactivateMemberById(){
        return null;
    }
}
