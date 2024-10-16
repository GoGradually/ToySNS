package toysns.toysns.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import toysns.toysns.domain.member.Address;
import toysns.toysns.domain.member.Member;

@Data
public class MemberInfoDto {
    private Long memberId;

    @Size(min = 5, max = 20, message = "사용자 이름은 5자에서 20자 사이여야 합니다.")
    private String username;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @Size(max = 100, message = "자기소개는 100자 이하여야 합니다.")
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
