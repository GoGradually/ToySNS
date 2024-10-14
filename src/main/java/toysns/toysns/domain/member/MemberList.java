package toysns.toysns.domain.member;

import lombok.Data;
import toysns.toysns.domain.member.Member;

import java.util.List;

@Data
public class MemberList {
    private final List<Member> memberList;
    private final String lastUsername;
}
