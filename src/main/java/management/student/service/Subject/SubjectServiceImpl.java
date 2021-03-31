package management.student.service.Subject;

import management.student.model.Subjects;
import management.student.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectServiceImpl implements ISubjectService{
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Page<Subjects> findAll(Pageable pageable) {
        return subjectRepository.findAll(pageable);
    }

    @Override
    public Iterable<Subjects> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Optional<Subjects> findById(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public Subjects save(Subjects subjects) {
        return subjectRepository.save(subjects);
    }

    @Override
    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }
}
