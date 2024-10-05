package toysns.toysns.domain.member.repository;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import toysns.toysns.domain.member.Member;
import toysns.toysns.domain.member.repository.MemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindByUsername() {
        // Given
        Member member = Member.builder()
                .username("testuser")
                .email("test@email.com")
                .build();
        entityManager.persist(member);
        entityManager.flush();

        // When
        Optional<Member> found = memberRepository.findByUsername("testuser");

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("testuser");
    }

    @Test
    void testFindByEmail() {
        // Given
        Member member = Member.builder()
                .username("testuser")
                .email("testuser@example.com")
                .build();
        entityManager.persist(member);
        entityManager.flush();

        // When
        Optional<Member> found = memberRepository.findByEmail("testuser@example.com");

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("testuser@example.com");
    }

    @Test
    void testFindByUsername_NotFound() {
        // When
        Optional<Member> notFound = memberRepository.findByUsername("nonexistent");

        // Then
        assertThat(notFound).isEmpty();
    }

    @Test
    void testFindByEmail_NotFound() {
        // When
        Optional<Member> notFound = memberRepository.findByEmail("nonexistent@example.com");

        // Then
        assertThat(notFound).isEmpty();
    }

    @Test
    void testSaveMember() {
        // Given
        Member member = Member.builder()
                .username("testuser")
                .email("test@email.com")
                .build();

        // When
        Member savedMember = memberRepository.save(member);

        // Then
        assertThat(savedMember.getId()).isNotNull();
        assertThat(entityManager.find(Member.class, savedMember.getId())).isEqualTo(savedMember);
    }
}