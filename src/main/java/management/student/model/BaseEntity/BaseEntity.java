package management.student.model.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @JsonIgnore
    @CreatedBy
    private String createBy;

    @JsonIgnore
    @CreatedBy
    private ZonedDateTime createDate;

    @JsonIgnore
    @CreatedBy
    private String updateBy;

    @JsonIgnore
    @CreatedBy
    private ZonedDateTime updateDate;

    @JsonIgnore
    @CreatedBy
    private Boolean status;
}
