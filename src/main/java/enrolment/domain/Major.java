package enrolment.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Major {

    @Id @GeneratedValue
    private Long id;

    private String majorName;

    @OneToMany(mappedBy = "major")
    private List<Course> courses = new ArrayList<>();


}
