package toysns.toysns.domain.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import toysns.toysns.domain.member.Address;
import toysns.toysns.domain.member.Member;
import toysns.toysns.domain.member.execption.ConflictEmailException;
import toysns.toysns.domain.member.execption.ConflictUsernameException;
import toysns.toysns.domain.member.execption.MemberNotFoundException;
import toysns.toysns.domain.member.service.MemberService;
import toysns.toysns.dto.MemberInfoDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
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
        Member member = Member.builder()
                .id(1L)
                .username("testId")
                .email("test@email.com")
                .introduce(newIntroduce)
                .address(new Address(null, null, null))
                .build();
        MemberInfoDto memberInfoDto = new MemberInfoDto(member); // 필요한 데이터 설정
        when(memberService.updateMemberIntroduce(eq(id), newIntroduce)).thenReturn(member);

        mockMvc.perform(post("/member/{id}/introduce", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newIntroduce))) // JSON 형식의 요청 본문
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(memberInfoDto)));

        verify(memberService).updateMemberIntroduce(eq(id), newIntroduce);
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
        when(memberService.updateMemberAddress(id, address)).thenReturn(member);

        mockMvc.perform(post("/member/{id}/address", id)
                .param("address", objectMapper.writeValueAsString(address)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(memberInfoDto)));

        verify(memberService).updateMemberAddress(eq(id), address);
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

        mockMvc.perform(get("/member/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(member)));

        verify(memberService).findMemberById(id);
    }

    @Test
    void findMembersByUsername() throws Exception {
        String username = "testUser";
        String lastUsername = "lastUser";
        List<Member> memberList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Member member = Member.builder()
                    .username("test" + i)
                    .email("test" + i + "@email.com")
                    .introduce("hello")
                    .address(new Address(null, null, null))
                    .build();
            memberList.add(member);
        }
        when(memberService.findMembersByUsername(username, lastUsername)).thenReturn(memberList);

        mockMvc.perform(get("/members")
                        .param("username", username)
                        .param("lastUsername", lastUsername))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(memberList.stream().map(MemberInfoDto::new))));

        verify(memberService).findMembersByUsername(username, lastUsername);
    }

    @Test
    void deleteMemberById() throws Exception {
        Long id = 1L;
        when(memberService.deleteMemberById(id)).thenReturn(LocalDateTime.of(2024, 10, 5, 0, 0,0));

        mockMvc.perform(post("/member/{id}", id))
                .andExpect(status().isOk());

        verify(memberService).deleteMemberById(id);
    }

    @Test
    void deactivateMemberById() throws Exception {
        Long id = 1L;
        when(memberService.deactivateMemberById(id)).thenReturn(true);

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
        when(memberService.createMember(member)).thenThrow(new ConflictUsernameException());
        mockMvc.perform(post("/member/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberInfoDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("This username is already exist"));

        verify(memberService).createMember(member);
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
        when(memberService.createMember(member)).thenThrow(new ConflictEmailException());

        mockMvc.perform(post("/member/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberInfoDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("This email is already exist"));

        verify(memberService).createMember(member);
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
}