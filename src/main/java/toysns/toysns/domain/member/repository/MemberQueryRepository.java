package toysns.toysns.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toysns.toysns.domain.member.Member;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Member> findMembersByUsername(){
        return null;
    }
}
