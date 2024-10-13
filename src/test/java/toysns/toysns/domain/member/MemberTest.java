package toysns.toysns.domain.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import toysns.toysns.execption.DeactivatedMemberException;
import toysns.toysns.execption.DeletedMemberException;

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

    //Todo
    @Test
    void 계정_활성화(){
        //given

        //when

        //then

    }

    //Todo
    @Test
    void 계정_비활성화(){
        //given

        //when

        //then

    }

    //Todo
    @Test
    void 계정_삭제(){
        //given

        //when

        //then

    }

    //Todo
    @Test
    void 계정_복구(){
        //given

        //when

        //then

    }

}