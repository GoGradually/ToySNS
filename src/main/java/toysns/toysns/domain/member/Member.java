package toysns.toysns.domain.member;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;
import toysns.toysns.execption.DeactivatedMemberException;
import toysns.toysns.execption.DeletedMemberException;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Builder.Default
    private boolean active = true;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", introduce='" + introduce + '\'' +
                ", active=" + active +
                ", deletedDateTime=" + deletedDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return active == member.active && Objects.equals(id, member.id) && Objects.equals(username, member.username) && Objects.equals(email, member.email) && Objects.equals(address, member.address) && Objects.equals(introduce, member.introduce) && Objects.equals(deletedDateTime, member.deletedDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, address, introduce, active, deletedDateTime);
    }

    @Builder.Default
    private LocalDateTime deletedDateTime = null;

    public boolean changeAddress(Address address){
        if(deletedDateTime != null) throw new DeletedMemberException();
        if(!active) throw new DeactivatedMemberException();
        this.address = address;
        return true;
    }

    public boolean changeIntroduce(String newIntroduce){
        if(deletedDateTime != null) throw new DeletedMemberException();
        if(!active) throw new DeactivatedMemberException();
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
    public void deleteAccount(LocalDateTime deletedDateTime) {
        if(this.deletedDateTime != null){
            throw new IllegalStateException();
        }
        this.deletedDateTime = deletedDateTime;
    }
    public void restoreAccount() {
        if(this.deletedDateTime == null){
            throw new IllegalStateException();
        }
        this.deletedDateTime = null;
    }
}
