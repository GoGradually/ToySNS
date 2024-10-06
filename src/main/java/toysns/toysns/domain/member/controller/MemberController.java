package toysns.toysns.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import toysns.toysns.domain.member.service.MemberService;
import toysns.toysns.dto.AddressDto;
import toysns.toysns.dto.MemberInfoDto;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member/create")
    public String createMember(MemberInfoDto memberInfoDto){
        return null;
    }

    @PostMapping("/member/{id}")
    public String updateMember(Long id, MemberInfoDto memberInfoDto){
        return null;
    }

    @GetMapping("/member/{id}")
    public String findMemberById(Long id){
        return null;
    }

    @GetMapping("/members")
    public String findMembersByUsername(String username, String lastUsername){
        return null;
    }

    @PostMapping("/member/{id}/delete")
    public String deleteMemberById(Long id){
        return null;
    }

    @PostMapping("/member/{id}/restore")
    public String restoreMemberById(Long id){
        return null;
    }

    @PostMapping("/member/{id}/activate")
    public String activateMemberById(Long id){
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
