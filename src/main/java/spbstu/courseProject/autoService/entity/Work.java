package spbstu.courseProject.autoService.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateWork;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Master master;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Car car;
    
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Service service;
}
