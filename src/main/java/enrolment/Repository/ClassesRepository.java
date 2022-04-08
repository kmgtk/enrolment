package enrolment.Repository;

import enrolment.domain.Classes;
import enrolment.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {

    @Query("select c from Classes c where c.course.id = :courseId")
    List<Classes> findClassByCourseId (@Param("courseId") Long courseId );

    @Query("select c from Classes c where c.id = :id")
    Classes findOneById(@Param("id") Long classesId);
}
