package toysns.toysns.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import toysns.toysns.dto.AddressDto;
import toysns.toysns.dto.MemberDto;

@RestController
public class MemberController {

    @PostMapping("/member/create")
    public String createMember(String username, String password, String email, String introduce, AddressDto addressDto){
        return null;
    }

    @PostMapping("/member/{id}")
    public String updateMember(Long id, MemberDto memberDto){
        return null;
    }

    @GetMapping("/member/{id}")
    public String findMemberById(Long id){
        return null;
    }

    @GetMapping("/members")
    public String findMembersByUsername(String username){
        return null;
    }

    @DeleteMapping("/member/{id}")
    public String deleteMemberById(Long id){
        return null;
    }

    @PostMapping("/member/{id}/deactivate")
    public String deactivateMemberById(Long id){
        return null;
    }

    @GetMapping("/check/email")
    public String checkEmail(String email){
        return null;
    }

    @GetMapping("/check/username")
    public String checkUsername(String username){
        return null;
    }
}
