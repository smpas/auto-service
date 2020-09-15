package spbstu.courseProject.autoService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spbstu.courseProject.autoService.auth.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    public ApplicationUser findByUsername(String s);
}
