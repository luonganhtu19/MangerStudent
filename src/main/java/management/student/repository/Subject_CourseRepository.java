package management.student.repository;


import management.student.model.Subject_Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Subject_CourseRepository extends JpaRepository<Subject_Course, Long> {
}
