package spbstu.courseProject.autoService.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationDto {
    private String username;
    private String password;
}
