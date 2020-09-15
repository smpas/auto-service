package spbstu.courseProject.autoService.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spbstu.courseProject.autoService.entity.Car;
import spbstu.courseProject.autoService.repository.CarRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/car")
@PreAuthorize("isAuthenticated()")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        try {
            var cars = StreamSupport
                    .stream(carRepository.findAll().spliterator(), false)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") Long id) {
        try {
            var car = carRepository.findById(id).get();

            return ResponseEntity.ok(car);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car car) {
        try {
            return ResponseEntity.ok(carRepository.save(car));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
        try {
            var carFromDb = carRepository.findById(id).get();

            BeanUtils.copyProperties(car, carFromDb, "id");

            return ResponseEntity.ok(carRepository.save(carFromDb));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCar(@PathVariable("id") Long id) {
        try {
            carRepository.deleteById(id);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
