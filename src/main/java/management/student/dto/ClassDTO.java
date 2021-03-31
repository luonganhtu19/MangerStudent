package management.student.dto;

import lombok.Data;
import management.student.model.Course;

import javax.validation.constraints.NotNull;

@Data
public class ClassDTO {
    private Long id;
    private String name;
    private Course course;
}
