package toysns.toysns.domain.member.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import toysns.toysns.domain.member.service.MemberService;
import toysns.toysns.dto.MemberInfoDto;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MemberControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    @BeforeEach
    void setUp() {
        // JUnit 5 에서 자동으로 닫아주기 때문에, 굳이 @AfterEach 사용 안해도 됨
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    void createMember() throws Exception {
        MemberInfoDto memberInfoDto = new MemberInfoDto(); // 필요한 데이터 설정
        when(memberService.createMember(any(MemberInfoDto.class))).thenReturn("Member created");

        mockMvc.perform(post("/member/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) // JSON 형식의 요청 본문
                .andExpect(status().isOk())
                .andExpect(content().string("Member created"));

        verify(memberService).createMember(any(MemberInfoDto.class));
    }

    @Test
    void updateMember() throws Exception {
        Long id = 1L;
        MemberInfoDto memberInfoDto = new MemberInfoDto(); // 필요한 데이터 설정
        when(memberService.updateMember(eq(id), any(MemberInfoDto.class))).thenReturn("Member updated");

        mockMvc.perform(post("/member/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) // JSON 형식의 요청 본문
                .andExpect(status().isOk())
                .andExpect(content().string("Member updated"));

        verify(memberService).updateMember(eq(id), any(MemberInfoDto.class));
    }

    @Test
    void findMemberById() throws Exception {
        Long id = 1L;
        when(memberService.findMemberById(id)).thenReturn("Member found");

        mockMvc.perform(get("/member/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Member found"));

        verify(memberService).findMemberById(id);
    }

    @Test
    void findMembersByUsername() throws Exception {
        String username = "testUser";
        String lastUsername = "lastUser";
        when(memberService.findMembersByUsername(username, lastUsername)).thenReturn("Members found");

        mockMvc.perform(get("/members")
                        .param("username", username)
                        .param("lastUsername", lastUsername))
                .andExpect(status().isOk())
                .andExpect(content().string("Members found"));

        verify(memberService).findMembersByUsername(username, lastUsername);
    }

    @Test
    void deleteMemberById() throws Exception {
        Long id = 1L;
        when(memberService.deleteMemberById(id)).thenReturn(LocalDateTime.of(2024, 10, 5, 0, 0,0));

        mockMvc.perform(delete("/member/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Member deleted"));

        verify(memberService).deleteMemberById(id);
    }

    @Test
    void deactivateMemberById() throws Exception {
        Long id = 1L;
        when(memberService.deactivateMemberById(id)).thenReturn(true);

        mockMvc.perform(post("/member/{id}/deactivate", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Member deactivated"));

        verify(memberService).deactivateMemberById(id);
    }

    @Test
    void checkEmail() throws Exception {
        String email = "test@example.com";
        when(memberService.checkEmail(email)).thenReturn(true);

        mockMvc.perform(get("/check/email")
                        .param("email", email))
                .andExpect(status().isOk())
                .andExpect(content().string("Email available"));

        verify(memberService).checkEmail(email);
    }

    @Test
    void checkUsername() throws Exception {
        String username = "testUser";
        when(memberService.checkUsername(username)).thenReturn(true);

        mockMvc.perform(get("/check/username")
                        .param("username", username))
                .andExpect(status().isOk())
                .andExpect(content().string("Username available"));

        verify(memberService).checkUsername(username);
    }
}