package toysns.toysns.domain.member.repository;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;
import toysns.toysns.domain.member.Address;
import toysns.toysns.domain.member.Member;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberQueryRepositoryTest {
    @Autowired
    private MemberQueryRepository memberQueryRepository;

    @Autowired
    private TestEntityManager em;

    @BeforeEach
    void setUp(){
        for (int i = 0; i < 10; i++) {
            Member member = Member.builder()
                    .username("test" + i)
                    .email("test" + i + "@email.com")
                    .introduce("hello")
                    .address(new Address("", "", ""))
                    .build();
            em.persist(member);
        }
    }
}