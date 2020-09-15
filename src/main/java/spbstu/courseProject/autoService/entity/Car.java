package spbstu.courseProject.autoService.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String numb;
    @Column(length = 20)
    private String color;
    @Column(length = 20)
    private String mark;
    private boolean isForeign;
}
