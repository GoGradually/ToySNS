package toysns.toysns.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toysns.toysns.domain.member.Address;
import toysns.toysns.domain.member.Member;
import toysns.toysns.domain.member.MemberList;
import toysns.toysns.domain.member.service.MemberService;
import toysns.toysns.dto.MemberInfoDto;
import toysns.toysns.dto.MemberInfoListDto;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member/create")
    public MemberInfoDto createMember(@RequestBody MemberInfoDto memberInfoDto){
        Member member = Member.builder()
                .username(memberInfoDto.getUsername())
                .email(memberInfoDto.getEmail())
                .introduce(memberInfoDto.getIntroduce())
                .address(memberInfoDto.getAddress())
                .build();
        Member createdMember = memberService.createMember(member);
        return new MemberInfoDto(createdMember);
    }

    @PostMapping("/member/{id}/introduce")
    public MemberInfoDto updateMemberIntroduce(@PathVariable Long id, @RequestBody String introduce){
        Member updatedMember = memberService.updateMemberIntroduce(id, introduce);
        return new MemberInfoDto(updatedMember);
    }

    @PostMapping("/member/{id}/address")
    public MemberInfoDto updateMemberAddress(@PathVariable Long id, @RequestBody Address address){
        Member member = memberService.updateMemberAddress(id, address);
        return new MemberInfoDto(member);
    }

    @GetMapping("/member/{id}")
    public MemberInfoDto findMemberById(@PathVariable Long id){
        Member findMember = memberService.findMemberById(id);
        return new MemberInfoDto(findMember);
    }

    @GetMapping("/members")
    public MemberInfoListDto findMembersByUsername(@RequestParam String username, @RequestParam String lastUsername){
        MemberList memberList = memberService.findMembersByUsername(username, lastUsername);
        return new MemberInfoListDto(memberList);
    }

    @PostMapping("/member/{id}/delete")
    public MemberInfoDto deleteMemberById(@PathVariable Long id){
        Member deletedMember = memberService.deleteMemberById(id);
        return new MemberInfoDto(deletedMember);
    }

    @PostMapping("/member/{id}/restore")
    public MemberInfoDto restoreMemberById(@PathVariable Long id){
        Member restoredMember = memberService.restoreMemberById(id);
        return new MemberInfoDto(restoredMember);
    }

    @PostMapping("/member/{id}/activate")
    public MemberInfoDto activateMemberById(@PathVariable Long id){
        Member activatedMember = memberService.activateMemberById(id);
        return new MemberInfoDto(activatedMember);
    }

    @PostMapping("/member/{id}/deactivate")
    public MemberInfoDto deactivateMemberById(@PathVariable Long id){
        Member deactivatedMember = memberService.deactivateMemberById(id);
        return new MemberInfoDto(deactivatedMember);
    }

    @GetMapping("/check/email")
    public boolean checkEmail(@RequestParam String email){
        return memberService.checkEmail(email);
    }

    @GetMapping("/check/username")
    public boolean checkUsername(@RequestParam String username){
        return memberService.checkUsername(username);
    }
}
