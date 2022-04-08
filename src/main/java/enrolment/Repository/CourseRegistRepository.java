package enrolment.Repository;

import enrolment.domain.TakeClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRegistRepository extends JpaRepository<TakeClass, Long> {



}
