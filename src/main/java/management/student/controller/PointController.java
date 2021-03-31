package management.student.controller;

import management.student.dto.ConverterDTO;
import management.student.dto.PointDTO;
import management.student.model.Point;
import management.student.service.Point.IPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/point")
public class PointController {
    @Autowired
    private IPointService iPointService;
    @Autowired
    private ConverterDTO converterDTO;

    @GetMapping
    public ResponseEntity<?> getAllPoint(){
        Iterable<Point> points =iPointService.findAll();
        List<PointDTO> pointList =new ArrayList<>();
        for (Point point: points) {
            pointList.add(converterDTO.pointDTOConverter(point));
        }
        if (pointList.size()==0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(pointList, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createNewSinger(@RequestBody Optional<PointDTO> pointDTO){
        Point point = new Point();
        if (pointDTO.isPresent()) {
            point = converterDTO.pointConverter(pointDTO.get());
            point.setStatus(true);
            point.setCreateBy(pointDTO.get().getUser().getFullName());
            point.setCreateDate(converterDTO.getTime());
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(iPointService.save(point), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSinger(@PathVariable Long id, @RequestBody PointDTO pointDTO){
        Optional<Point> pointOptional = iPointService.findById(id);
        return pointOptional.map(point -> {
            pointDTO.setId(point.getId());
            Point pointSave = iPointService.save(converterDTO.pointConverter(pointDTO));
            return new ResponseEntity<>(pointSave, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Point> deletePoint(@PathVariable Long id){
        Optional<Point> pointOptional = iPointService.findById(id);
        return pointOptional.map(singer -> {
            iPointService.deleteById(id);
            return new ResponseEntity<Point>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
