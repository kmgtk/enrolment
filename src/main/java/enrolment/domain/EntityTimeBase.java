package enrolment.domain;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class EntityTimeBase {

    @Column(updatable = false)
    private  LocalDateTime createDate;

    private  LocalDateTime updateDate;

    @PrePersist
    public void createDate(){
        createDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void updateDate(){
        updateDate = LocalDateTime.now();
    }
}
