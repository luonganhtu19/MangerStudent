package management.student.dto;

import lombok.Data;
import management.student.model.Class;
import management.student.model.Point;
import management.student.service.Course.ICourseService;
import management.student.service.Point.IPointService;
import management.student.service.Subject.ISubjectService;
import management.student.service.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@Component
public class ConverterDTO {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICourseService iCourseService;
    @Autowired
    private ISubjectService iSubjectService;

    public ZonedDateTime getTime(){
        ZoneId zoneHCM = ZoneId.of("Asia/Ho_Chi_Minh");
        // Creating LocalDateTime by providing input arguments
        LocalDateTime today = LocalDateTime.now();
        // Creating ZonedDateTime by providing input arguments
        ZonedDateTime hcmDateTime = ZonedDateTime.of(today, zoneHCM);
        return hcmDateTime;
    }
    public Class classConverter(ClassDTO classDTO){
        Class classModel = new Class();
        classModel.setId(classDTO.getId());
        classModel.setName(classDTO.getName());
        classModel.setCourseId(classDTO.getCourse().getId());
        classModel.setCreateDate(getTime());
        return classModel;
    }
    public ClassDTO classDTOConverter(Class classModel){
        ClassDTO classDTO =new ClassDTO();
        classDTO.setId(classModel.getId());
        classDTO.setName(classModel.getName());
        classDTO.setCourse(iCourseService.findById(classModel.getCourseId()).get());
        return classDTO;
    }
    public PointDTO pointDTOConverter(Point point){
        PointDTO pointDTO = new PointDTO();
        pointDTO.setId(point.getId());
        pointDTO.setSubject(iSubjectService.findById(point.getSubjectId()).get());
        pointDTO.setUser(iUserService.findById(point.getUserId()).get());
        pointDTO.setPoint(point.getPoint());
        return pointDTO;
    }
    public Point pointConverter(PointDTO pointDTO){
        Point point = new Point();
        point.setId(pointDTO.getId());
        point.setPoint(pointDTO.getPoint());
        point.setSubjectId(pointDTO.getSubject().getId());
        point.setUserId(pointDTO.getUser().getId());
        return point;
    }
}
