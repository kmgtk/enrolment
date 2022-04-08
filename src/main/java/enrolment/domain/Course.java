package enrolment.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Course {

    @Id @GeneratedValue
    @Column(name = "course_id")
    private Long id;

    private String courseName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAJOR_ID")
    private Major major;


    @OneToMany(mappedBy = "course")
    private List<Classes> classes = new ArrayList<>();



}
