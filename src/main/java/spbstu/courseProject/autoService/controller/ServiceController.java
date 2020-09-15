package spbstu.courseProject.autoService.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spbstu.courseProject.autoService.entity.Service;
import spbstu.courseProject.autoService.repository.ServiceRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/service")
@PreAuthorize("isAuthenticated()")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public ResponseEntity<List<Service>> getServiceList() {
        try {
            var serviceList = StreamSupport
                    .stream(serviceRepository.findAll().spliterator(), false)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(serviceList);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Service> getService(@PathVariable("id") Long id) {
        try {
            var service = serviceRepository.findById(id).get();

            return ResponseEntity.ok(service);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Service> saveService(@RequestBody Service service) {
        try {
            return ResponseEntity.ok(serviceRepository.save(service));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Service> updateService(@PathVariable("id") Long id, @RequestBody Service service) {
        try {
            var serviceFromDb = serviceRepository.findById(id).get();

            BeanUtils.copyProperties(service, serviceFromDb, "id");

            return ResponseEntity.ok(serviceRepository.save(serviceFromDb));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteService(@PathVariable("id") Long id) {
        try {
            serviceRepository.deleteById(id);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
