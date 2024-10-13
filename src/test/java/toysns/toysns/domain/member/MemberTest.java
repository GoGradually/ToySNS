package toysns.toysns.domain.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import toysns.toysns.execption.DeactivatedMemberException;
import toysns.toysns.execption.DeletedMemberException;

import java.time.LocalDateTime;

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
    void testChangeAddressDeactivated() {
        // Given
        Member member = Member.builder()
                .username("testuser")
                .address(new Address("Old City", "Old Street", "00000"))
                .build();
        Address newAddress = new Address("New City", "New Street", "12345");

        // When
        assertThrows(DeactivatedMemberException.class, () -> member.changeAddress(newAddress));
    }
    @Test
    void testChangeAddressDeleted() {
        // Given
        Member member = Member.builder()
                .username("testuser")
                .address(new Address("Old City", "Old Street", "00000"))
                .build();
        Address newAddress = new Address("New City", "New Street", "12345");

        // When
        assertThrows(DeletedMemberException.class, () -> member.changeAddress(newAddress));
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
        boolean result = member.changeIntroduce(newIntroduce);

        // Then
        assertThat(result).isTrue();
        assertThat(member.getIntroduce()).isEqualTo(newIntroduce);
    }


    @Test
    void testChangeIntroduceDeactivated() {
        // Given
        String oldIntroduction = "Old introduction";
        Member member = Member.builder()
                .username("testuser")
                .introduce(oldIntroduction)
                .build();
        String newIntroduce = "New introduction but deactivatedMember";

        // When
        assertThrows(DeactivatedMemberException.class, () -> member.changeIntroduce(newIntroduce));
    }
    @Test
    void testChangeIntroduceDeleted() {
        // Given
        String oldIntroduction = "Old introduction";
        Member member = Member.builder()
                .username("testuser")
                .introduce(oldIntroduction)
                .build();
        String newIntroduce = "New introduction but deactivatedMember";

        // When
        assertThrows(DeletedMemberException.class, () -> member.changeIntroduce(newIntroduce));
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

    @Test
    void 계정_활성화_성공(){
        //given
        Member member = Member.builder()
                .username("test")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address("", "", ""))
                .active(false)
                .build();
        //when
        member.activate();
        //then
        assertThat(member.isActive()).isTrue();

    }
    @Test
    void 계정_활성화_실패(){
        //given
        Member member = Member.builder()
                .username("test")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address("", "", ""))
                .active(true)
                .build();
        //when
        //then
        assertThrows(IllegalStateException.class, member::activate);
        assertThat(member.isActive()).isTrue();

    }
    @Test
    void 계정_비활성화_성공(){
        //given
        Member member = Member.builder()
                .username("test")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address("", "", ""))
                .active(true)
                .build();

        //when
        member.deactivate();
        //then
        assertThat(member.isActive()).isFalse();

    }
    @Test
    void 계정_비활성화_실패(){
        //given
        Member member = Member.builder()
                .username("test")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address("", "", ""))
                .active(false)
                .build();

        //when
        assertThrows(IllegalStateException.class, member::activate);
        assertThat(member.isActive()).isFalse();

    }

    @Test
    void 계정_삭제_성공(){
        //given
        Member member = Member.builder()
                .username("test")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address("", "", ""))
                .build();

        //when
        member.deleteAccount(LocalDateTime.of(2024,10,10,0,0,0));
        //then
        assertThat(member.getDeletedDateTime()).isNotNull();

    }
    @Test
    void 계정_삭제_실패(){
        //given
        Member member = Member.builder()
                .username("test")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address("", "", ""))
                .deletedDateTime(LocalDateTime.of(2024,10,10,0,0,0))
                .build();

        //when
        assertThrows(IllegalStateException.class, ()->member.deleteAccount(LocalDateTime.of(2024,10,15,0,0,0)));
        assertThat(member.getDeletedDateTime()).isNotNull();

    }

    @Test
    void 계정_복구_성공(){
        //given
        Member member = Member.builder()
                .username("test")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address("", "", ""))
                .deletedDateTime(LocalDateTime.of(2024,10,10,0,0,0))
                .build();

        //when
        member.restoreAccount();
        //then
        assertThat(member.getDeletedDateTime()).isNull();
    }
    @Test
    void 계정_복구_실패(){
        //given
        Member member = Member.builder()
                .username("test")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address("", "", ""))
                .build();

        //when

        //then
        assertThrows(IllegalStateException.class, member::restoreAccount);
        assertThat(member.getDeletedDateTime()).isNull();

    }

}