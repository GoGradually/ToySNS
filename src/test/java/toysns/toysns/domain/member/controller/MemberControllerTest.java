package toysns.toysns.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import toysns.toysns.domain.member.Address;
import toysns.toysns.domain.member.Member;
import toysns.toysns.domain.member.MemberList;
import toysns.toysns.dto.MemberInfoListDto;
import toysns.toysns.execption.*;
import toysns.toysns.domain.member.service.MemberService;
import toysns.toysns.dto.MemberInfoDto;
import toysns.toysns.execption.advice.MemberControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MemberControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // JUnit 5 에서 자동으로 닫아주기 때문에, 굳이 @AfterEach 사용 안해도 됨
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController)
                .setControllerAdvice(new MemberControllerAdvice())
                .build();
    }

    @Test
    void createMember() throws Exception {
        Member member = Member.builder()
                .username("testId")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address(null, null, null))
                .build();
        MemberInfoDto memberInfoDto = new MemberInfoDto(member); // 필요한 데이터 설정
        when(memberService.createMember(any(Member.class))).thenReturn(member);

        mockMvc.perform(post("/member/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberInfoDto))) // JSON 형식의 요청 본문
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(memberInfoDto)));

        verify(memberService).createMember(any(Member.class));
    }

    @Test
    void updateMemberIntroduce() throws Exception {
        Long id = 1L;
        String newIntroduce = "let me introduce";
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("introduce", newIntroduce);
        Member member = Member.builder()
                .id(1L)
                .username("testId")
                .email("test@email.com")
                .introduce(newIntroduce)
                .address(new Address(null, null, null))
                .build();
        MemberInfoDto memberInfoDto = new MemberInfoDto(member); // 필요한 데이터 설정
        when(memberService.updateMemberIntroduce(eq(id), eq(newIntroduce))).thenReturn(member);

        mockMvc.perform(post("/member/{id}/introduce", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody))) // JSON 형식의 요청 본문
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(memberInfoDto)));

        verify(memberService).updateMemberIntroduce(eq(id), eq(newIntroduce));
    }

    @Test
    void updateMemberAddress() throws Exception{
        Address address = new Address("hello", "hi", "34221");
        Long id = 1L;

        Member member = Member.builder()
                .id(id)
                .username("testId")
                .email("test@email.com")
                .introduce("hello")
                .address(address)
                .build();
        MemberInfoDto memberInfoDto = new MemberInfoDto(member);
        Map<String, Address> requestBody = new HashMap<>();
        requestBody.put("address", address);
        when(memberService.updateMemberAddress(id, address)).thenReturn(member);

        mockMvc.perform(post("/member/{id}/address", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(memberInfoDto)));

        verify(memberService).updateMemberAddress(id, address);
    }

    @Test
    void findMemberById() throws Exception {
        Long id = 1L;
        Member member = Member.builder()
                .id(id)
                .username("testId")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address(null, null, null))
                .build();
        when(memberService.findMemberById(id)).thenReturn(member);

        MemberInfoDto memberInfoDto = new MemberInfoDto(member);

        mockMvc.perform(get("/member/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(memberInfoDto)));

        verify(memberService).findMemberById(id);
    }

    @Test
    void findMembersByUsername() throws Exception {
        String username = "testUser";
        String lastUsername = "lastUser";
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Member member = Member.builder()
                    .username("test" + i)
                    .email("test" + i + "@email.com")
                    .introduce("hello")
                    .address(new Address(null, null, null))
                    .build();
            members.add(member);
            lastUsername = "test" + i;
        }
        MemberList memberList = new MemberList(members, lastUsername);
        when(memberService.findMembersByUsername(username, lastUsername)).thenReturn(memberList);

        mockMvc.perform(get("/members")
                        .param("username", username)
                        .param("lastUsername", lastUsername))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new MemberInfoListDto(memberList))));

        verify(memberService).findMembersByUsername(username, lastUsername);
    }

    @Test
    void deleteMemberById() throws Exception {
        Long id = 1L;
        Member member = Member.builder()
                .username("testId")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address(null, null, null))
                .deletedDateTime(LocalDateTime.of(2024, 10, 10, 0, 0, 0))
                .build();
        when(memberService.deleteMemberById(id)).thenReturn(member);

        mockMvc.perform(post("/member/{id}/delete", id))
                .andExpect(status().isOk());

        verify(memberService).deleteMemberById(id);
    }

    @Test
    void deactivateMemberById() throws Exception {
        Long id = 1L;
        Member member = Member.builder()
                .id(id)
                .username("testId")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address(null, null, null))
                .active(false)
                .build();
        when(memberService.deactivateMemberById(id)).thenReturn(member);

        mockMvc.perform(post("/member/{id}/deactivate", id))
                .andExpect(status().isOk());

        verify(memberService).deactivateMemberById(id);
    }

    @Test
    void checkEmail() throws Exception {
        String email = "test@example.com";
        when(memberService.checkEmail(email)).thenReturn(true);

        mockMvc.perform(get("/check/email")
                        .param("email", email))
                .andExpect(status().isOk());

        verify(memberService).checkEmail(email);
    }

    @Test
    void checkUsername() throws Exception {
        String username = "testUser";
        when(memberService.checkUsername(username)).thenReturn(true);

        mockMvc.perform(get("/check/username")
                        .param("username", username))
                .andExpect(status().isOk());

        verify(memberService).checkUsername(username);
    }

    @Test
    void createMember_이미_존재하는_Username() throws Exception {
        Member member = Member.builder()
                .username("testId")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address(null, null, null))
                .build();
        MemberInfoDto memberInfoDto = new MemberInfoDto(member);
        when(memberService.createMember(any(Member.class))).thenThrow(new ConflictUsernameException());
        mockMvc.perform(post("/member/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberInfoDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("This username is already exist"));

        verify(memberService).createMember(any(Member.class));
    }

    @Test
    void createMember_이미_존재하는_Email() throws Exception {
        Member member = Member.builder()
                .username("testId")
                .email("test@email.com")
                .introduce("hello")
                .address(new Address(null, null, null))
                .build();
        MemberInfoDto memberInfoDto = new MemberInfoDto(member);
        when(memberService.createMember(any(Member.class))).thenThrow(new ConflictEmailException());

        mockMvc.perform(post("/member/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberInfoDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("This email is already exist"));

        verify(memberService).createMember(any(Member.class));
    }

    @Test
    void findMember_사용자를_찾을_수_없음() throws Exception {
        Long id = 1L;
        when(memberService.findMemberById(id)).thenThrow(new MemberNotFoundException());

        mockMvc.perform(get("/member/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Member Not Found"));

        verify(memberService).findMemberById(id);
    }
    @Test
    void findMember_삭제된_사용자() throws Exception {
        Long id = 1L;
        when(memberService.findMemberById(id)).thenThrow(new DeletedMemberException());

        mockMvc.perform(get("/member/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Member Not Found"));

        verify(memberService).findMemberById(id);
    }

    @Test
    void findMember_비활성화된_사용자() throws Exception {
        Long id = 1L;
        when(memberService.findMemberById(id)).thenThrow(new DeactivatedMemberException());

        mockMvc.perform(get("/member/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Member Not Found"));

        verify(memberService).findMemberById(id);
    }
}