package toysns.toysns.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toysns.toysns.domain.member.Member;
import toysns.toysns.dto.MemberDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    public Member createMember(MemberDto memberDto){
        return null;
    }
    public Member findMemberById(Long memberId){
        return null;
    }
    public List<Member> findMembersByUsername(String username){
        return null;
    }
    public List<Member> findNeighborMembersByUsername(Long memberId, String username){
        return null;
    }

    public boolean checkEmail(String email){
        return false;
    }

    public boolean checkUsername(String username){
        return false;
    }

    public Member updateMember(MemberDto memberDto){
        return null;
    }
    public void deleteMember(Long memberId){
    }
    public Member deactivateMember(Long memberId){
        return null;
    }
}
