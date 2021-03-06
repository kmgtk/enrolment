package enrolment.Service;

import enrolment.Repository.MajorRepository;
import enrolment.Repository.UserRepository;
import enrolment.domain.TakeClass;
import enrolment.domain.User;
import enrolment.dto.signUpDto;
import enrolment.dto.userUpdateRequestDto;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public Long join(signUpDto signUpDto){

        userRepository.findByloginId(signUpDto.getLoginId())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("Already Exist ID");
                });

        if(!signUpDto.getPassword().equals(signUpDto.getPasswordConfirm())){
            throw new IllegalArgumentException("Please Check Password!");
        }

        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        signUpDto.setPassword(encodedPassword);

        User user = User.signupBuilder()
                .loginId(signUpDto.getLoginId())
                .password(signUpDto.getPassword())
                .userName(signUpDto.getUsername())
                .major(majorRepository.findById(signUpDto.getMajorId()).get())
                .email(signUpDto.getEmail())
                .phoneNumber(signUpDto.getPhoneNumber()).build();

        return userRepository.save(user).getId();
    }

    @Transactional

    public List<TakeClass> getMyClass(Long userId){

        List<TakeClass> takeClasses = userRepository.findOneById(userId).getTakeClasses();

        return takeClasses;

    }

    public userUpdateRequestDto getUserInfo(String longinId){

        User userInfo = userRepository.findUserInfoByloginId(longinId);
        userUpdateRequestDto userUpdateRequestDto =
                enrolment.dto.userUpdateRequestDto.builder().userName(userInfo.getUsername())
                                                            .loginId(userInfo.getLoginId())
                .email(userInfo.getEmail())
                .phoneNumber(userInfo.getPhoneNumber())
                .majorName(userInfo.getMajor().getMajorName()).build();

        return userUpdateRequestDto;

    }

    @Transactional
    public Long updateUserInfo(userUpdateRequestDto userUpdateRequestDto){

       User user =  userRepository.findOneByloginId(userUpdateRequestDto.getLoginId());

       user.updateEmailInfo(userUpdateRequestDto.getEmail());
       user.updatePhoneNumberInfo(userUpdateRequestDto.getPhoneNumber());

       return user.getId();

    }

}
