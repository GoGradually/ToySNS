package toysns.toysns.domain.member.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import toysns.toysns.domain.member.Address;
import toysns.toysns.domain.member.Member;
import toysns.toysns.domain.member.repository.MemberRepository;
import toysns.toysns.dto.AddressDto;
import toysns.toysns.dto.MemberDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;
    @InjectMocks
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){

    }

    @Test
    public void 신규_회원_생성_성공(){
        //given
        MemberDto memberDto = new MemberDto("testId", "test@email.com", "hello", new AddressDto(null, null, null));
        Member member = Member.builder()
                .username("testId")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address(null, null, null))
                .build();


        when(memberRepository.save(any(Member.class))).thenReturn(member);

        //when
        Member savedMember = memberService.createMember(memberDto);

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
        MemberDto newMemberDto = new MemberDto("existingId", null, null, new AddressDto(null, null, null));
        when(memberRepository.findByUsername("existingId")).thenReturn(java.util.Optional.of(existingMember));

        //when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            memberService.createMember(newMemberDto);
        });

        //then
        assertEquals("이미 존재하는 ID입니다.", exception.getMessage());
        verify(memberRepository, never()).save(any(Member.class));

    }

    @Test
    public void 신규_회원_생성_동일한email(){
        //given
        Member existingMember = Member.builder()
                .username("existingId")
                .email("existing@email.com")
                .build();
        MemberDto newMemberDto = new MemberDto("newId", "existing@email.com", null, null);
        when(memberRepository.findByEmail("existing@email.com")).thenReturn(java.util.Optional.of(existingMember));

        //when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            memberService.createMember(newMemberDto);
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
    public void 회원_정보_수정(){
        //given
        Long memberId = 100L;
        Member originalMember = Member.builder()
                .id(memberId)
                .username("test")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address("", "", ""))
                .build();
        Member updatedMember = Member.builder()
                .id(memberId)
                .username("test")
                .email("test@email.com")
                .introduce("hi")
                .address(new Address("", "", ""))
                .build();
        MemberDto updateMemberDto = new MemberDto(updatedMember);
        when(memberRepository.findById(memberId)).thenReturn(java.util.Optional.of(originalMember));
        when(memberRepository.save(any(Member.class))).thenReturn(updatedMember);

        //when
        Member result = memberService.updateMember(memberId, updateMemberDto);

        //then
        assertNotNull(result);
        assertEquals("hi", result.getIntroduce());
        verify(memberRepository, times(1)).findById(memberId);
        verify(memberRepository, times(1)).save(any(Member.class));

    }

}