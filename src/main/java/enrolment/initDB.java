package enrolment;

import enrolment.domain.Classes;
import enrolment.domain.Course;
import enrolment.domain.Major;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class initDB {

    private final InitService initService;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void initDB(){

        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Major major = new Major();
            major.setMajorName("컴퓨터정보통신공학과");
            Major major1 = new Major();
            major1.setMajorName("기계공학과");
            Major major2 = new Major();
            major2.setMajorName("화학과");
            em.persist(major);
            em.persist(major1);
            em.persist(major2);

            List<Major> majors = new ArrayList<>();

            majors.add(major);
            majors.add(major1);
            majors.add(major2);

            for (Major forMajor : majors) {

                Course course = new Course();
                course.setCourseName(forMajor.getMajorName()+"1");
                course.setMajor(forMajor);

                Course course1 = new Course();
                course1.setCourseName(forMajor.getMajorName()+"2");
                course1.setMajor(forMajor);

                Course course2 = new Course();
                course2.setCourseName(forMajor.getMajorName()+"3");
                course2.setMajor(forMajor);

                em.persist(course);
                em.persist(course1);
                em.persist(course2);

                List<Course> list = new ArrayList<>();
                list.add(course);
                list.add(course1);
                list.add(course2);

                for (Course forCourse : list) {

                    Classes classes = new Classes();
                    classes.setProfessorName("A");
                    classes.setMaxStudentNum(30);
                    classes.setClassNumber(01);
                    classes.setCurStudentNum(0);
                    classes.setCourse(forCourse);

                    Classes classes1 = new Classes();
                    classes1.setProfessorName("B");
                    classes1.setMaxStudentNum(30);
                    classes1.setClassNumber(02);
                    classes1.setCurStudentNum(0);
                    classes1.setCourse(forCourse);

                    Classes classes2 = new Classes();
                    classes2.setProfessorName("C");
                    classes2.setMaxStudentNum(30);
                    classes2.setClassNumber(03);
                    classes2.setCurStudentNum(0);
                    classes2.setCourse(forCourse);

                    em.persist(classes);
                    em.persist(classes1);
                    em.persist(classes2);

                }



                major.setCourses(list);
            }






        }
    }

}
