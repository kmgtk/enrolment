package enrolment.Repository;

import enrolment.domain.Course;
import enrolment.domain.Major;
import enrolment.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class MajorRepositoryTest {


    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findByName() {

        List<Major> majors = majorRepository.findAll();

        for (Major major : majors) {
            List<Course> courses = courseRepository.findByMajor(major.getId());

            System.out.println(major.getMajorName());
            for (Course cours : courses) {
                System.out.println(cours.getCourseName());
            }
        }


/*        List<Major> majors = majorRepository.findAll();
        for (Major major : majors) {
            System.out.println(major.getMajorName());
        }
*/

/*
        User user = new
        user.setUserName("aaa");
    userRepository.save(user);
*/

    }
}