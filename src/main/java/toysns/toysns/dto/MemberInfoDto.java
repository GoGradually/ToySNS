package toysns.toysns.dto;

import lombok.Data;
import toysns.toysns.domain.member.Address;
import toysns.toysns.domain.member.Member;

@Data
public class MemberInfoDto {
    private Long memberId;
    private String username;
    private String email;
    private String introduce;
    private Address address;

    public MemberInfoDto(String username, String email, String introduce, Address address) {
        this.username = username;
        this.email = email;
        this.introduce = introduce;
        this.address = address;
    }

    public MemberInfoDto(Member member){
        memberId = member.getId();
        username = member.getUsername();
        email = member.getEmail();
        introduce = member.getIntroduce();
        address = member.getAddress();
    }

    public MemberInfoDto() {
    }
}
