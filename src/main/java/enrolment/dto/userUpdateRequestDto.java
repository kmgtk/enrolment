package enrolment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class userUpdateRequestDto {

    private String userName;
    private String loginId;
    private String email;
    private String phoneNumber;
    private String majorName;

    @Builder
    public userUpdateRequestDto (String userName, String loginId, String email, String phoneNumber, String majorName){
        this.userName = userName;
        this.loginId = loginId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.majorName = majorName;
    }

}
