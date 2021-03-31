package management.student.dto;

import lombok.Data;
import management.student.model.Subjects;
import management.student.model.user.User;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
public class PointDTO {
    private Long id;
    private User user;
    private Subjects subject;
    private Long point;
}
