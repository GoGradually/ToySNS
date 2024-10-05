package toysns.toysns.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toysns.toysns.domain.member.Member;
import toysns.toysns.domain.member.repository.MemberQueryRepository;
import toysns.toysns.domain.member.repository.MemberRepository;
import toysns.toysns.dto.MemberInfoDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    public Member createMember(MemberInfoDto memberInfoDto){
        return null;
    }
    public Member findMemberById(Long memberId){
        return null;
    }
    public List<Member> findMembersByUsername(String username, String lastUsername){
        return null;
    }

    public boolean checkEmail(String email){
        return false;
    }

    public boolean checkUsername(String username){
        return false;
    }

    public Member updateMember(Long memberId, MemberInfoDto memberInfoDto){
        return null;
    }
}
