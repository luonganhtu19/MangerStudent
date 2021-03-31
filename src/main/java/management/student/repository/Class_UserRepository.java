package management.student.repository;


import management.student.model.Class_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Class_UserRepository extends JpaRepository<Class_User, Long> {
}
