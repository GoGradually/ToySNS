package toysns.toysns.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toysns.toysns.domain.member.Member;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    public Member createMember(){
        return null;
    }
    public Member findMemberById(){
        return null;
    }
    public List<Member> findMembersByUsername(){
        return null;
    }
    public List<Member> findMembersByUsernameOrderByFriends(){
        return null;
    }

    public Member updateMember(){
        return null;
    }
    public void deleteMember(){
    }
    public Member deactivateMember(){
        return null;
    }
}
