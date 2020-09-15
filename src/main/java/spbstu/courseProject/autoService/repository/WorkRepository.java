package spbstu.courseProject.autoService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spbstu.courseProject.autoService.entity.Work;

@Repository
public interface WorkRepository extends CrudRepository<Work, Long> {
}
