package toysns.toysns.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toysns.toysns.domain.member.Address;
import toysns.toysns.domain.member.Member;
import toysns.toysns.domain.member.repository.MemberQueryRepository;
import toysns.toysns.domain.member.repository.MemberRepository;
import toysns.toysns.dto.MemberInfoDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final Clock clock;

    public Member createMember(Member member){
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

    public Member updateMemberIntroduce(Long memberId, String newIntroduce){
        return null;
    }

    public Member updateMemberAddress(Long memberId, Address address){
        return null;
    }

    public Member deactivateMemberById(Long id) {
        return null;
    }

    public Member activateMemberById(Long id){
        return null;
    }

    public Member deleteMemberById(Long id) {
        return null;
    }

    public Member restoreMemberById(Long id){
        return null;
    }
}
