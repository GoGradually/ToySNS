package toysns.toysns.domain.member;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;

    private String email;

    @Embedded
    private Address address;

    private String introduce;

    public void changeAddress(Address address){
        this.address = address;
    }

    public void changeIntroduce(String newIntroduce){
        this.introduce = newIntroduce;
    }
}
