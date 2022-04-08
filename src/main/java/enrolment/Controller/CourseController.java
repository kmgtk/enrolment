package enrolment.Controller;


import enrolment.Service.ClassesService;
import enrolment.Service.CourseService;
import enrolment.Service.MajorService;
import enrolment.domain.Classes;
import enrolment.domain.Course;
import enrolment.domain.Major;
import enrolment.dto.classSearchDto;
import enrolment.dto.signUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    MajorService majorService;


    @GetMapping("/courses")
    public String searchCourse(@ModelAttribute("classSearch") classSearchDto classSearchDto, Model model){

        List<Major> majors = majorService.findAll();
        model.addAttribute("majors", majors);

        if(classSearchDto.getMajorId()!=null){
            List<Course> courses = courseService.findByMajor(classSearchDto.getMajorId());
            model.addAttribute("courses", courses);
        }





        return "courseList";
    }
}
