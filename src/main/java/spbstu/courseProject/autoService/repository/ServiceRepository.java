package spbstu.courseProject.autoService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spbstu.courseProject.autoService.entity.Service;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {
}
