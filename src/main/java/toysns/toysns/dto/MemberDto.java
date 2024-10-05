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

    public MemberDto(String username, String email, String introduce, AddressDto addressDto) {
        this.username = username;
        this.email = email;
        this.introduce = introduce;
        this.addressDto = addressDto;
    }

    public MemberDto(Member member){
        memberId = member.getId();
        username = member.getUsername();
        email = member.getEmail();
        introduce = member.getIntroduce();
        addressDto = new AddressDto(member.getAddress());
    }
}
