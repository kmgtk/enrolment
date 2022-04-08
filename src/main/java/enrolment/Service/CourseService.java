package enrolment.Service;

import enrolment.Repository.CourseRepository;
import enrolment.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> findByMajor(Long majorId) {
        return courseRepository.findByMajor(majorId);
    }


}
