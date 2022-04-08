package enrolment.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Classes {

    @Id @GeneratedValue
    @Column(name = "class_id")
    private Long id;

    private int classNumber;
    private int curStudentNum;
    private int maxStudentNum;
    private String professorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID")
    private Course course;


    @OneToMany(mappedBy = "classes")
    private List<TakeClass> takeClasses = new ArrayList<>();

    public void registClasee(){
        curStudentNum++;
    }

    public void cancel() {curStudentNum--;}


}
