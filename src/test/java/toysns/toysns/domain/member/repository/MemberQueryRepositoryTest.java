package toysns.toysns.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import toysns.toysns.config.DatabaseConfig;
import toysns.toysns.domain.member.Address;
import toysns.toysns.domain.member.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({DatabaseConfig.class, MemberQueryRepository.class})
class MemberQueryRepositoryTest {
    @Autowired
    private MemberQueryRepository memberQueryRepository;

    @Autowired
    private TestEntityManager em;

    @BeforeEach
    void setUp(){
        for (int i = 0; i <2; i++) {
            Member member = Member.builder()
                    .username("tesst" + i)
                    .email("tesst" + i + "@email.com")
                    .introduce("hello")
                    .address(new Address("", "", ""))
                    .build();
            em.persist(member);
        }
        for (int i = 0; i < 10; i++) {
            Member member = Member.builder()
                    .username("test" + i)
                    .email("test" + i + "@email.com")
                    .introduce("hello")
                    .address(new Address("", "", ""))
                    .build();
            em.persist(member);
        }
        em.flush();
        em.clear();
    }

    @Test
    void findMembersByUsername_10개_조회(){
        //given

        //when
        List<Member> findMembers = memberQueryRepository.findMembersByUsername("test", null);
        //then

        assertThat(findMembers).hasSize(10);
        assertThat(findMembers).allMatch(m -> m.getUsername().startsWith("test"));
    }
    @Test
    void findMembersByUsername_0개_조회(){
        //given

        //when
        List<Member> findMembers = memberQueryRepository.findMembersByUsername("nothing", null);

        //then
        assertThat(findMembers).isNotNull();
        assertThat(findMembers).hasSize(0);
    }

    @Test
    void findMembersByUsername_10개_조회_limit(){
        //given
        for (int i = 10; i < 20; i++) {
            Member member = Member.builder()
                    .username("test" + i)
                    .email("test" + i + "@email.com")
                    .introduce("hello")
                    .address(new Address("", "", ""))
                    .build();
            em.persist(member);
        }
        em.flush();
        em.clear();
        //when
        List<Member> findMembers = memberQueryRepository.findMembersByUsername("test", null);

        //then
        assertThat(findMembers).hasSize(10);
        assertThat(findMembers).allMatch(m -> m.getUsername().startsWith("test"));

    }
    @Test
    void findMembersByUsername_1개_조회(){
        //when
        List<Member> findMembers = memberQueryRepository.findMembersByUsername("test5", null);

        //then
        assertThat(findMembers).hasSize(1);
        assertThat(findMembers).allMatch(m -> m.getUsername().startsWith("test5"));
    }

    @Test
    void findMembersByUsername_5개_조회_lastUsername(){
        //given

        //when
        List<Member> findMembers = memberQueryRepository.findMembersByUsername("test", "test4");

        //then
        assertThat(findMembers).hasSize(5);
        assertThat(findMembers).allMatch(m -> m.getUsername().startsWith("test"));
        assertThat(findMembers).allMatch(m->m.getUsername().compareTo("test4") > 0);
    }

    @Test
    void findMembersByUsername_10개_조회_limit_lastUsername(){
        //given
        for (int i = 10; i < 20; i++) {
            Member member = Member.builder()
                    .username("test" + i)
                    .email("test" + i + "@email.com")
                    .introduce("hello")
                    .address(new Address("", "", ""))
                    .build();
            em.persist(member);
        }
        em.flush();
        em.clear();

        //when
        List<Member> findMembers = memberQueryRepository.findMembersByUsername("test", "test17");

        //then
        assertThat(findMembers).hasSize(10);
        assertThat(findMembers).allMatch(m -> m.getUsername().startsWith("test"));
        assertThat(findMembers).allMatch(m->m.getUsername().compareTo("test17") > 0);
    }
}