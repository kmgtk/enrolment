package enrolment.Controller;

import enrolment.Repository.CourseRepository;
import enrolment.Repository.MajorRepository;
import enrolment.Service.ClassesService;
import enrolment.Service.CourseRegistService;
import enrolment.Service.CourseService;
import enrolment.Service.MajorService;
import enrolment.domain.Classes;
import enrolment.domain.Course;
import enrolment.domain.Major;
import enrolment.domain.User;
import enrolment.dto.classSearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CourseRegisterController {

    @Autowired
    private MajorService majorService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ClassesService classesService;

    @Autowired
    private CourseRegistService courseRegistService;

   @GetMapping("/register")
    public String register(@ModelAttribute("classSearch") classSearchDto classSearchDto, Model model) {


       List<Major> majorList = majorService.findAll();

       model.addAttribute("majors",majorList);

       if(classSearchDto.getMajorId()!=null){
           List<Course> courses = courseService.findByMajor(classSearchDto.getMajorId());
           model.addAttribute("courses", courses);
       }

       if(classSearchDto.getCourseId()!=null){
           List<Classes> classesList = classesService.findClassByCourseId(classSearchDto.getCourseId());
           model.addAttribute("classes", classesList);
       }

       return "courseRegistration";

   }

   @GetMapping("/register/{id}")
    public String saveMyCourse(@PathVariable("id") Long classId , @AuthenticationPrincipal User user){

       courseRegistService.saveTakeClass(classId,  user.getId());


       return "redirect:/home";
   }

   @GetMapping("/cancel/{id}")
    public String cancelMyCourse(@PathVariable("id") Long id){

       courseRegistService.deleteTakeClass(id);

       return "redirect:/home";
   }

}
