package spbstu.courseProject.autoService.service;

import spbstu.courseProject.autoService.dto.WorkDto;
import spbstu.courseProject.autoService.entity.Work;

import java.util.List;

public interface WorkService {
    public List<Work> getAll();
    public Work getById(final Long id);
    public Work save(final WorkDto workDto);
    public Work update(final Long id, final WorkDto workDto);
    public void delete(final Long id);
}
