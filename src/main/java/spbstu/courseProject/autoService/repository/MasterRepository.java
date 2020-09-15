package spbstu.courseProject.autoService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spbstu.courseProject.autoService.entity.Master;

@Repository
public interface MasterRepository extends CrudRepository<Master, Long> {
}
