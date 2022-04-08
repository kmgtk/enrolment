package enrolment.Service;

import enrolment.Repository.ClassesRepository;
import enrolment.Repository.CourseRegistRepository;
import enrolment.Repository.UserRepository;
import enrolment.domain.Classes;
import enrolment.domain.TakeClass;
import enrolment.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CourseRegistService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private CourseRegistRepository courseRegistRepository;

    @Transactional
    public void saveTakeClass(Long classId, Long userId){



        User user = userRepository.findOneById(userId);
        Classes classes = classesRepository.findOneById(classId);

        TakeClass takeClass = new TakeClass();
        takeClass.setClasses(classes);
        takeClass.setUser(user);
        classes.registClasee();

        courseRegistRepository.save(takeClass);

    }


    @Transactional
    public void deleteTakeClass(Long id){

        Classes classes = courseRegistRepository.findById(id).get().getClasses();
        classes.cancel();
        courseRegistRepository.deleteById(id);


    }
}
