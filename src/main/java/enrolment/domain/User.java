package enrolment.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends EntityTimeBase implements UserDetails {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String loginId;
    private String password;
    private String phoneNumber;
    private String role;
    private String userName;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAJOR_ID")
    private Major major;

    @OneToMany(mappedBy = "user")
    List<TakeClass> takeClasses = new ArrayList<>();

    @Builder(builderClassName = "UserSignUpBuilder", builderMethodName = "signupBuilder")
    public User(String loginId, String password, String userName, Major major, String email, String phoneNumber){
        this.loginId = loginId;
        this.password = password;
        this.userName = userName;
        this.major = major;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = Role.STUDENT.getValue();
        this.takeClasses = new ArrayList<>();
    }

    public void updateEmailInfo(String email){
        this.email = email;
    }

    public void updatePhoneNumberInfo(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
