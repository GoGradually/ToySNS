package toysns.toysns.domain.member.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import toysns.toysns.domain.member.Address;
import toysns.toysns.domain.member.Member;
import toysns.toysns.domain.member.repository.MemberQueryRepository;
import toysns.toysns.domain.member.repository.MemberRepository;
import toysns.toysns.dto.MemberInfoDto;
import toysns.toysns.domain.member.execption.ConflictEmailException;
import toysns.toysns.domain.member.execption.ConflictUsernameException;
import toysns.toysns.domain.member.execption.MemberNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;
    @Mock
    MemberQueryRepository memberQueryRepository;
    @InjectMocks
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){

    }

    @Test
    public void 신규_회원_생성_성공(){
        //given
        MemberInfoDto memberInfoDto = new MemberInfoDto("testId", "test@email.com", "hello", new Address(null, null, null));
        Member member = Member.builder()
                .username("testId")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address(null, null, null))
                .build();


        when(memberRepository.save(any(Member.class))).thenReturn(member);

        //when
        Member savedMember = memberService.createMember(member);

        //then
        assertNotNull(savedMember);
        assertEquals("testId", savedMember.getId());
        assertEquals("test@email.com", savedMember.getEmail());
        verify(memberRepository, times(1)).save(any(Member.class));


    }
    @Test
    public void 신규_회원_생성_동일한Username(){
        //given
        Member existingMember = Member.builder()
                .username("existingId")
                .build();
        MemberInfoDto newMemberInfoDto = new MemberInfoDto("existingId", null, null, new Address(null, null, null));
        when(memberRepository.findByUsername("existingId")).thenReturn(java.util.Optional.of(existingMember));

        //when
        Exception exception = assertThrows(ConflictUsernameException.class, () ->
                memberService.createMember(existingMember));

        //then
        assertEquals("이미 존재하는 ID입니다.", exception.getMessage());
        verify(memberRepository, never()).save(any(Member.class));

    }

    @Test
    public void 신규_회원_생성_동일한email(){
        //given
        Member existingMember = Member.builder()
                .username("nonExistingId")
                .email("existing@email.com")
                .build();
        MemberInfoDto newMemberInfoDto = new MemberInfoDto("newId", "existing@email.com", null, null);
        when(memberRepository.findByEmail("existing@email.com")).thenReturn(java.util.Optional.of(existingMember));

        //when
        Exception exception = assertThrows(ConflictEmailException.class, () -> {
            memberService.createMember(existingMember);
        });

        //then
        assertEquals("이미 존재하는 이메일입니다.", exception.getMessage());
        verify(memberRepository, never()).save(any(Member.class));
    }

    @Test
    public void 단일_회원_정보_조회(){
        //given
        Long memberId = 100L;
        Member member = Member.builder()
                        .username("testId")
                                .build();
        when(memberRepository.findById(memberId)).thenReturn(java.util.Optional.of(member));

        //when
        Member foundMember = memberService.findMemberById(memberId);

        //then
        assertNotNull(foundMember);
        assertEquals(memberId, foundMember.getId());
        assertEquals("test@email.com", foundMember.getEmail());
        verify(memberRepository, times(1)).findById(memberId);
    }
    @Test
    public void 단일_회원_정보_조회_존재하지_않는_ID(){
        //given
        Long memberId = 100L;
        when(memberRepository.findById(memberId)).thenReturn(java.util.Optional.empty());

        //when

        //then
        assertThrows(MemberNotFoundException.class, ()-> memberService.findMemberById(memberId));
        verify(memberRepository, times(1)).findById(memberId);
    }

    @Test
    public void 회원_소개_수정(){
        //given
        Long memberId = 100L;
        Member originalMember = Member.builder()
                .id(memberId)
                .username("test")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address("", "", ""))
                .build();
        String newIntroduce = "hi";
        when(memberRepository.findById(memberId)).thenReturn(java.util.Optional.of(originalMember));

        //when
        Member result = memberService.updateMemberIntroduce(memberId, newIntroduce);

        //then
        assertNotNull(result);
        assertEquals("hi", result.getIntroduce());
        verify(memberRepository, times(1)).findById(memberId);

    }

    @Test
    void 회원_주소_수정(){
        //given
        Long memberId = 100L;
        Member originalMember = Member.builder()
                .id(memberId)
                .username("test")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address("", "", ""))
                .build();
        Address newAddress = new Address("new", "city", "haha");
        when(memberRepository.findById(memberId)).thenReturn(java.util.Optional.of(originalMember));

        //when
        Member result = memberService.updateMemberAddress(memberId, newAddress);

        //then
        assertNotNull(result);
        assertEquals(newAddress, result.getAddress());
        verify(memberRepository, times(1)).findById(memberId);

    }

    @Test
    void email_검사_존재O(){
        //given
        Member member = Member.builder()
                .username("test")
                .email("test@email.com")
                .build();
        when(memberRepository.findByEmail("test@email.com")).thenReturn(Optional.ofNullable(member));

        //when
        boolean result = memberService.checkEmail("test@email.com");

        //then
        assertThat(result).isFalse();
    }

    @Test
    void email_검사_존재X(){
        //given
        when(memberRepository.findByEmail("test@email.com")).thenReturn(Optional.empty());

        //when
        boolean result = memberService.checkEmail("test@email.com");

        //then
        assertThat(result).isTrue();
    }
    @Test
    void username_검사_존재O(){
        //given
        Member member = Member.builder()
                        .username("test")
                                .build();
        when(memberRepository.findByUsername("test")).thenReturn(Optional.ofNullable(member));

        //when
        boolean result = memberService.checkUsername("test");

        //then
        assertThat(result).isFalse();
    }
    @Test
    void username_검사_존재X(){
        //given
        when(memberRepository.findByUsername("test")).thenReturn(Optional.empty());

        //when
        boolean result = memberService.checkUsername("test");

        //then
        assertThat(result).isTrue();
    }


    @Test
    void MemberQueryRepository_사용자_검색_호출_성공() {
        List<Member> mockUsers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            mockUsers.add(
                    Member.builder()
                            .id((long) i)
                            .username("user" + i)
                            .build()
            );
        }

        when(memberQueryRepository.findMembersByUsername("user",null)).thenReturn(mockUsers);

        List<Member> result = memberService.findMembersByUsername("user", null);

        assertEquals(10, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("User 10", result.get(9).getUsername());

        verify(memberQueryRepository, times(1)).findMembersByUsername("user", null);
    }

    @Test
    void MemberQueryRepository_사용자_검색_호출_존재X() {
        List<Member> mockUsers = new ArrayList<>();

        when(memberQueryRepository.findMembersByUsername("user",null)).thenReturn(mockUsers);

        List<Member> result = memberService.findMembersByUsername("user", null);

        assertEquals(10, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("User 10", result.get(9).getUsername());

        verify(memberQueryRepository, times(1)).findMembersByUsername("user", null);
    }

    //Todo
    @Test
    void findById_삭제된_계정(){
        //given

        //when

        //then

    }

    //Todo
    @Test
    void findById_비활성화된_계정(){
        //given

        //when

        //then

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