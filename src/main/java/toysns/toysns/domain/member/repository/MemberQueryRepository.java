package toysns.toysns.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toysns.toysns.domain.member.Member;
import toysns.toysns.domain.member.QMember;

import java.util.List;

import static toysns.toysns.domain.member.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Member> findMembersByUsername(){
        queryFactory.select();
        return null;
    }
}
