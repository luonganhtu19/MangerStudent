package management.student.service.User;


import management.student.model.user.User;
import management.student.service.IGenericService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGenericService<User>, UserDetailsService {
}
