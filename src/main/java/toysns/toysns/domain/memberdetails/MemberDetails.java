package toysns.toysns.domain.memberdetails;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import toysns.toysns.domain.membermemberdetails.MemberMemberDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "username"
                })
        }
)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDetails implements UserDetails {

    @Id
    @Column(name = "member_details_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;

    private String password;

    private String email;

    @Builder.Default
    @OneToMany(mappedBy = "memberDetails")
    private List<MemberMemberDetails> authorities = new ArrayList<>();

    @Setter
    @Builder.Default
    private boolean accountNonExpired = true;
    @Setter
    @Builder.Default
    private boolean accountNonLocked = true;
    @Setter
    @Builder.Default
    private boolean credentialsNonExpired = true;
    @Setter
    @Builder.Default
    private boolean enabled = true;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
