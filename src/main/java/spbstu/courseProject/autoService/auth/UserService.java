package spbstu.courseProject.autoService.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spbstu.courseProject.autoService.dto.RegistrationDto;
import spbstu.courseProject.autoService.exception.DuplicatedUsernameException;
import spbstu.courseProject.autoService.repository.ApplicationUserRepository;

import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private ApplicationUserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findByUsername(s);
    }

    public UserDetails addUser(RegistrationDto registrationDto) throws DuplicatedUsernameException {
        final boolean userExists = repository.findByUsername(registrationDto.getUsername()) != null;

        if (userExists) {
            throw new DuplicatedUsernameException();
        }

        var user = new ApplicationUser();
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setUsername(registrationDto.getUsername());
        user.setRoleSet(Set.of(Role.USER));

        return repository.save(user);
    }
}
