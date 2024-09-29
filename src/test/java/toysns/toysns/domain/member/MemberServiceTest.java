package toysns.toysns.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

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
        
        //when

        //then

    }
    @Test
    public void 신규_회원_생성_동일한ID(){
        //given
        
        //when
        
        //then
        
    }
    
    @Test
    public void 신규_회원_생성_동일한email(){
        //given
        
        //when
        
        //then
        
    }

    @Test
    public void 단일_회원_정보_조회(){
        //given

        //when

        //then

    }

    @Test
    public void 회원_정보_수정(){
        //given

        //when

        //then

    }

    @Test
    public void 회원_비활성화(){
        //given

        //when

        //then

    }
}