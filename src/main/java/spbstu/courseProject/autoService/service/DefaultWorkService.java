package spbstu.courseProject.autoService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spbstu.courseProject.autoService.dto.WorkDto;
import spbstu.courseProject.autoService.entity.Work;
import spbstu.courseProject.autoService.repository.CarRepository;
import spbstu.courseProject.autoService.repository.MasterRepository;
import spbstu.courseProject.autoService.repository.ServiceRepository;
import spbstu.courseProject.autoService.repository.WorkRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DefaultWorkService implements WorkService {
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private MasterRepository masterRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<Work> getAll() {
        return StreamSupport.stream(workRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Work getById(Long id) {
        return workRepository.findById(id).get();
    }

    @Override
    public Work save(WorkDto workDto) {
        var work = new Work();

        return workRepository.save(fillWorkFromDto(workDto, work));
    }

    @Override
    public Work update(final Long id, WorkDto workDto) {
        var work = workRepository.findById(id).get();

        return workRepository.save(fillWorkFromDto(workDto, work));
    }

    @Override
    public void delete(Long id) {
        workRepository.deleteById(id);
    }

    private Work fillWorkFromDto(WorkDto workDto, Work work) {
        var master = masterRepository.findById(workDto.getMasterId()).get();
        var car = carRepository.findById(workDto.getCarId()).get();
        var service = serviceRepository.findById(workDto.getServiceId()).get();

        work.setDateWork(workDto.getDateWork());
        work.setMaster(master);
        work.setCar(car);
        work.setService(service);

        return work;
    }
}
