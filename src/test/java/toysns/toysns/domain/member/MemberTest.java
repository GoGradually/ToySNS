package toysns.toysns.domain.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @Test
    void testMemberBuilder() {
        // Given
        Address address = new Address("City", "Street", "12345");

        // When
        Member member = Member.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .address(address)
                .introduce("Hello, I'm a test user")
                .build();

        // Then
        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getUsername()).isEqualTo("testuser");
        assertThat(member.getEmail()).isEqualTo("test@example.com");
        assertThat(member.getAddress()).isEqualTo(address);
        assertThat(member.getIntroduce()).isEqualTo("Hello, I'm a test user");
    }

    @Test
    void testChangeAddress() {
        // Given
        Member member = Member.builder()
                .username("testuser")
                .address(new Address("Old City", "Old Street", "00000"))
                .build();
        Address newAddress = new Address("New City", "New Street", "12345");

        // When
        member.changeAddress(newAddress);

        // Then
        assertThat(member.getAddress()).isEqualTo(newAddress);
    }

    @Test
    void testChangeIntroduce() {
        // Given
        Member member = Member.builder()
                .username("testuser")
                .introduce("Old introduction")
                .build();
        String newIntroduce = "New introduction";

        // When
        member.changeIntroduce(newIntroduce);

        // Then
        assertThat(member.getIntroduce()).isEqualTo(newIntroduce);
    }

    @Test
    void testNoArgsConstructor() {
        // When
        Member member = new Member();

        // Then
        assertThat(member).isNotNull();
        assertThat(member.getId()).isNull();
        assertThat(member.getUsername()).isNull();
        assertThat(member.getEmail()).isNull();
        assertThat(member.getAddress()).isNull();
        assertThat(member.getIntroduce()).isNull();
    }
}