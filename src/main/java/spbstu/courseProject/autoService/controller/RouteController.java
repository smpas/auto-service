package spbstu.courseProject.autoService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {
    @RequestMapping("/templates/login.html")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/templates/registration.html")
    public String registrationPage() {
        return "registration";
    }

    @RequestMapping("/templates/masters.html")
    public String mastersPage() {
        return "masters";
    }

    @RequestMapping("/templates/services.html")
    public String servicesPage() {
        return "services";
    }

    @RequestMapping("/templates/works.html")
    public String worksPage() {
        return "works";
    }

    @RequestMapping("/templates/cars.html")
    public String carsPage() {
        return "cars";
    }

    @RequestMapping("/")
    public String mainPage() {
        return "masters";
    }
}
