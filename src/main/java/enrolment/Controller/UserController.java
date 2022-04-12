package enrolment.Controller;

import enrolment.Repository.MajorRepository;
import enrolment.Service.MajorService;
import enrolment.Service.UserService;
import enrolment.domain.Major;
import enrolment.domain.TakeClass;
import enrolment.domain.User;
import enrolment.dto.signUpDto;
import enrolment.dto.userUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    MajorService majorService;

    @Autowired
    UserService userService;


    @GetMapping("/signup")
    public String signup(Model model, @RequestParam(value="msg", required = false) String msg){

        List<Major> findMajors = majorService.findAll();

        model.addAttribute("signUpDto", new signUpDto());
        model.addAttribute("majors", findMajors);
        model.addAttribute("msg", msg);

        return "signup";
    }

    @PostMapping("/signup")
    public String createUser(signUpDto signUpDto, Model model){

        try{
            userService.join(signUpDto);
        }catch (Exception e){
            List<Major> findMajors = majorService.findAll();
            model.addAttribute("signUpDto", signUpDto);
            model.addAttribute("majors", findMajors);
            model.addAttribute("msg", e.getMessage());
            return "signup";
        }


        return "redirect:/home";
    }

    @GetMapping("/myCourses")
    public String myCoursesList(Model model, @AuthenticationPrincipal User user){

        List<TakeClass> takeClasses = userService.getMyClass(user.getId());
        model.addAttribute("username",user.getUsername());
        model.addAttribute("takeClasses", takeClasses);

        return "myCourseList";
    }

    @GetMapping("/edit")
    public String myInfoEdit(Model model, @AuthenticationPrincipal User user){


        userUpdateRequestDto userUpdateRequestDto = userService.getUserInfo( user.getLoginId());

        model.addAttribute("userUpdateRequestDto",userUpdateRequestDto);

        return "edit";
    }

    @PostMapping("/edit")
    public String updateMyInfo(userUpdateRequestDto requestDto){

        userService.updateUserInfo(requestDto);

        return "redirect:/home";

    }

}
