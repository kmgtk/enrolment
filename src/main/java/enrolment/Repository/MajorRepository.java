package enrolment.Repository;

import enrolment.domain.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {


}
