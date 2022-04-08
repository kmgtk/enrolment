package enrolment.Controller;

import enrolment.domain.User;
import jdk.nashorn.internal.objects.NativeDebug;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @RequestMapping("/home")
    public String home(Model model){

        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if (user != "anonymousUser") {
            model.addAttribute("user", (User) user);
        }

        return "home";
    }
}
