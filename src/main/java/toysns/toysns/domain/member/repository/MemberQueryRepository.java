package toysns.toysns.domain.member.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
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

    public List<Member> findMembersByUsername(String username, String lastUsername){
        return queryFactory.select(member)
                .from(member)
                .where(usernameStartWith(username), usernameGt(lastUsername))
                .limit(10)
                .fetch();
    }

    private BooleanExpression usernameStartWith(String usernameCond){
        return usernameCond == null ? null : member.username.startsWith(usernameCond);
    }
    private BooleanExpression usernameGt(String lastUsernameCond){
        return lastUsernameCond == null ? null : member.username.gt(lastUsernameCond);
    }
}
