package toysns.toysns.dto;

import lombok.Data;
import toysns.toysns.domain.member.Member;

@Data
public class MemberDto {
    private Long memberId;
    private String username;
    private String email;
    private String introduce;
    private AddressDto addressDto;

    public MemberDto(Member member){
        memberId = member.getId();
        username = member.getUsername();
        email = member.getEmail();
        introduce = member.getIntroduce();
        addressDto = new AddressDto(member.getAddress());
    }
}
