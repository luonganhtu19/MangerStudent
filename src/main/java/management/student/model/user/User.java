package management.student.model.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import management.student.model.BaseEntity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String username;
    @Size(min = 6 , max = 100)
    private String password;
    @Email
    private String email;
    private String phone;
    private String address;
    private String pathImg;
    private String fullName;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}

