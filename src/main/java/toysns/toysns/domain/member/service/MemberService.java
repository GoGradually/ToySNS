package toysns.toysns.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toysns.toysns.domain.member.Address;
import toysns.toysns.domain.member.Member;
import toysns.toysns.domain.member.MemberList;
import toysns.toysns.domain.member.repository.MemberQueryRepository;
import toysns.toysns.domain.member.repository.MemberRepository;
import toysns.toysns.dto.MemberInfoDto;
import toysns.toysns.execption.DeactivatedMemberException;
import toysns.toysns.execption.DeletedMemberException;
import toysns.toysns.execption.MemberNotFoundException;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final Clock clock;

    public Member createMember(Member member){
        memberRepository.save(member);
        return member;
    }
    public Member findMemberById(Long memberId){
        return memberRepository.findById(memberId)
                .map(member -> {
                    if(member.getDeletedDateTime()!=null) throw new DeletedMemberException();
                    if(!member.isActive()) throw new DeactivatedMemberException();
                    return member;
                })
                .orElseThrow(MemberNotFoundException::new);
    }
    public MemberList findMembersByUsername(String username, String lastUsername){
        List<Member> result = memberQueryRepository.findMembersByUsername(username, lastUsername);
        String nextLastUsername = null;
        if(!result.isEmpty()){
            nextLastUsername = result.getLast().getUsername();
        }
        return new MemberList(
                result.stream()
                        .filter(m-> m.isActive() && m.getDeletedDateTime() == null)
                        .collect(Collectors.toList()),
                nextLastUsername);
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
