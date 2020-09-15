package spbstu.courseProject.autoService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spbstu.courseProject.autoService.entity.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
}
