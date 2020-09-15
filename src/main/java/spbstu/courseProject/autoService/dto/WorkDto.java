package spbstu.courseProject.autoService.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WorkDto {
    private LocalDate dateWork;
    private Long masterId;
    private Long carId;
    private Long serviceId;
}
