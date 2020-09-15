package spbstu.courseProject.autoService.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spbstu.courseProject.autoService.entity.Master;
import spbstu.courseProject.autoService.repository.MasterRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping("api/master")
@PreAuthorize("isAuthenticated()")
public class MasterController {
    @Autowired
    private MasterRepository masterRepository;

    @GetMapping
    public ResponseEntity<List<Master>> getMasterList() {
        try {
            var masterList = StreamSupport
                    .stream(masterRepository.findAll().spliterator(), false)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(masterList);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Master> getMaster(@PathVariable("id") Long id) {
        try {
            var master = masterRepository.findById(id).get();

            return ResponseEntity.ok(master);

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Master> saveMaster(@RequestBody Master master) {
        try {
            return ResponseEntity.ok(masterRepository.save(master));
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Master> updateMaster(@PathVariable("id") Long id, @RequestBody Master master) {
        try {
            var masterFromDb = masterRepository.findById(id).get();

            BeanUtils.copyProperties(master, masterFromDb, "id");

            return ResponseEntity.ok(masterRepository.save(masterFromDb));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteMaster(@PathVariable("id") Long id) {
        try {
            masterRepository.deleteById(id);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
