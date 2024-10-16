package toysns.toysns.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toysns.toysns.domain.member.Address;
import toysns.toysns.domain.member.service.MemberService;
import toysns.toysns.dto.MemberInfoDto;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member/create")
    public String createMember(@RequestBody MemberInfoDto memberInfoDto){
        return null;
    }

    @PostMapping("/member/{id}/introduce")
    public String updateMemberIntroduce(@PathVariable Long id, @RequestBody String introduce){
        return null;
    }

    @PostMapping("/member/{id}/address")
    public String updateMemberAddress(@PathVariable Long id, @RequestBody Address address){
        return null;
    }

    @GetMapping("/member/{id}")
    public String findMemberById(@PathVariable Long id){
        return null;
    }

    @GetMapping("/members")
    public String findMembersByUsername(@RequestParam String username, @RequestParam String lastUsername){
        return null;
    }

    @PostMapping("/member/{id}/delete")
    public String deleteMemberById(@PathVariable Long id){
        return null;
    }

    @PostMapping("/member/{id}/restore")
    public String restoreMemberById(@PathVariable Long id){
        return null;
    }

    @PostMapping("/member/{id}/activate")
    public String activateMemberById(@PathVariable Long id){
        return null;
    }

    @PostMapping("/member/{id}/deactivate")
    public String deactivateMemberById(@PathVariable Long id){
        return null;
    }

    @GetMapping("/check/email")
    public String checkEmail(@RequestParam String email){
        return null;
    }

    @GetMapping("/check/username")
    public String checkUsername(@RequestParam String username){
        return null;
    }
}
