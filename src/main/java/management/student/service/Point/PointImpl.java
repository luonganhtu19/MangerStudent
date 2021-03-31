package management.student.service.Point;

import management.student.model.Point;
import management.student.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PointImpl implements IPointService{
    @Autowired
    private PointRepository pointRepository;
    @Override
    public Page<Point> findAll(Pageable pageable) {
        return pointRepository.findAll(pageable);
    }

    @Override
    public Iterable<Point> findAll() {
        return pointRepository.findAll();
    }

    @Override
    public Optional<Point> findById(Long id) {
        return pointRepository.findById(id);
    }

    @Override
    public Point save(Point point) {
        return pointRepository.save(point);
    }

    @Override
    public void deleteById(Long id) {
        pointRepository.deleteById(id);
    }
}
