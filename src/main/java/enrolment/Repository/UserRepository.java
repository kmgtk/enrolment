package enrolment.Repository;

import enrolment.domain.Classes;
import enrolment.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByuserName(String name);

    @Query("select u from User u where u.id = :id")
    User findOneById (@Param("id") Long id);


    @Query("select u from User u where u.loginId = :loginId")
    User findOneByloginId (@Param("loginId") String loginId );


    Optional<User> findByloginId(String loginId);



}
