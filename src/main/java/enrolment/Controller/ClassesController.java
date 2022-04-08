package enrolment.Controller;

import enrolment.Repository.ClassesRepository;
import enrolment.domain.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ClassesController {

    @Autowired
    private ClassesRepository classesRepository;

    @GetMapping("/courses/{id}")
    public String classList (@PathVariable("id") Long courseId, Model model){
        List<Classes> classesList = classesRepository.findClassByCourseId(courseId);

        model.addAttribute("classes", classesList);

        return "classList";
    }
}
