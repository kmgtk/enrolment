package enrolment.Service;

import enrolment.Repository.ClassesRepository;
import enrolment.domain.Classes;
import enrolment.domain.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassesService {

    private final ClassesRepository classesRepository;

    public List<Classes> findClassByCourseId(Long courseId){
        return classesRepository.findClassByCourseId(courseId);
    }
}
