package spbstu.courseProject.autoService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spbstu.courseProject.autoService.dto.WorkDto;
import spbstu.courseProject.autoService.entity.Work;
import spbstu.courseProject.autoService.service.DefaultWorkService;
import spbstu.courseProject.autoService.service.WorkService;

import java.util.List;

@RestController
@RequestMapping("api/work")
@PreAuthorize("isAuthenticated()")
public class WorkController {
    @Autowired
    private WorkService workService;

    public WorkController(DefaultWorkService workService) {
        this.workService = workService;
    }

    @GetMapping
    public ResponseEntity<List<Work>> getWorkList() {
        try {

            return ResponseEntity.ok(workService.getAll());
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Work> getWork(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(workService.getById(id));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Work> saveWork(@RequestBody WorkDto workDto) {
        try {
            return ResponseEntity.ok(workService.save(workDto));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Work> updateWork(@PathVariable("id") Long id, @RequestBody WorkDto workDto) {
        try {
            return ResponseEntity.ok(workService.update(id, workDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteWork(@PathVariable("id") Long id) {
        try {
            workService.delete(id);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
