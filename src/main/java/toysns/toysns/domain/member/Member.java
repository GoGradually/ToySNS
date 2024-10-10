package toysns.toysns.domain.member;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

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

    private boolean active = true;

    private LocalDateTime deletedDateTime = null;

    public boolean changeAddress(Address address){
        this.address = address;
        return true;
    }

    public boolean changeIntroduce(String newIntroduce){
        this.introduce = newIntroduce;
        return true;
    }

    public void activate(){
        if(this.active){
            throw new IllegalStateException();
        }
        this.active = true;
    }
    public void deactivate() {
        if(!this.active){
            throw new IllegalStateException();
        }
        this.active = false;
    }
    public void deleteAccount() {
        if(this.deletedDateTime != null){
            throw new IllegalStateException();
        }
        this.deletedDateTime = LocalDateTime.now();
    }
    public void restoreAccount() {
        if(this.deletedDateTime == null){
            throw new IllegalStateException();
        }
        this.deletedDateTime = null;
    }
}
