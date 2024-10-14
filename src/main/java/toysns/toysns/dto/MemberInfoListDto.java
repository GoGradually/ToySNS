package toysns.toysns.dto;

import lombok.Data;
import toysns.toysns.domain.member.MemberList;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberInfoListDto {
    private final List<MemberInfoDto> memberInfoDtoList;
    private final String lastUsername;

    public MemberInfoListDto(List<MemberInfoDto> memberInfoDtoList, String lastUsername) {
        this.memberInfoDtoList = memberInfoDtoList;
        this.lastUsername = lastUsername;
    }

    public MemberInfoListDto(MemberList memberList){
        memberInfoDtoList = memberList.getMemberList().stream().map(MemberInfoDto::new).collect(Collectors.toList());
        lastUsername = memberList.getLastUsername();
    }
}
