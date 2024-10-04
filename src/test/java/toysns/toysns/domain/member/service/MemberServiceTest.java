package toysns.toysns.domain.member.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import toysns.toysns.domain.member.repository.MemberRepository;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        Member newMember = new Member("testId", "test@email.com", "password");
        when(memberRepository.save(any(Member.class))).thenReturn(newMember);

        //when
        Member savedMember = memberService.createMember(newMember);

        //then
        assertNotNull(savedMember);
        assertEquals("testId", savedMember.getId());
        assertEquals("test@email.com", savedMember.getEmail());
        verify(memberRepository, times(1)).save(any(Member.class));


    }
    @Test
    public void 신규_회원_생성_동일한ID(){
        //given
        Member existingMember = new Member("existingId", "existing@email.com", "password");
        Member newMember = new Member("existingId", "new@email.com", "password");
        when(memberRepository.findById("existingId")).thenReturn(java.util.Optional.of(existingMember));

        //when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            memberService.createMember(newMember);
        });

        //then
        assertEquals("이미 존재하는 ID입니다.", exception.getMessage());
        verify(memberRepository, never()).save(any(Member.class));

    }

    @Test
    public void 신규_회원_생성_동일한email(){
        //given
        Member existingMember = new Member("existingId", "existing@email.com", "password");
        Member newMember = new Member("newId", "existing@email.com", "password");
        when(memberRepository.findByEmail("existing@email.com")).thenReturn(java.util.Optional.of(existingMember));

        //when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            memberService.createMember(newMember);
        });

        //then
        assertEquals("이미 존재하는 이메일입니다.", exception.getMessage());
        verify(memberRepository, never()).save(any(Member.class));

    }

    @Test
    public void 단일_회원_정보_조회(){
        //given
        String memberId = "testId";
        Member member = new Member(memberId, "test@email.com", "password");
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
        Member originalMember = Member.builder()
                .id(1L)
                .username("name")
                .email("old@email.com")
                .build();
        Member updatedMember = Member.builder()
                .username("name")
                .email("new@email.com")
                .build();
        when(memberRepository.findById(1L)).thenReturn(java.util.Optional.of(originalMember));
        when(memberRepository.save(any(Member.class))).thenReturn(updatedMember);

        //when
        Member result = memberService.updateMember(memberId, updatedMember);

        //then
        assertNotNull(result);
        assertEquals("new@email.com", result.getEmail());
        verify(memberRepository, times(1)).findById(memberId);
        verify(memberRepository, times(1)).save(any(Member.class));

    }

    @Test
    public void 회원_비활성화(){
        //given
        String memberId = "testId";
        Member member = new Member(memberId, "test@email.com", "password");
        member.setActive(true);
        when(memberRepository.findById(memberId)).thenReturn(java.util.Optional.of(member));
        when(memberRepository.save(any(Member.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //when
        Member deactivatedMember = memberService.deactivateMember(memberId);

        //then
        assertFalse(deactivatedMember.isActive());
        verify(memberRepository, times(1)).findById(memberId);
        verify(memberRepository, times(1)).save(any(Member.class));
    }
}