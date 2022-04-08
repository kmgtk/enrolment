package enrolment.Service;

import enrolment.Repository.MajorRepository;
import enrolment.domain.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {

    @Autowired
    MajorRepository majorRepository;

    public List<Major> findAll(){
        return majorRepository.findAll();
    }
}
