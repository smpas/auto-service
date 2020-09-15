package spbstu.courseProject.autoService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class AutoServiceApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(AutoServiceApplication.class, args);
            openHomePage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void openHomePage() throws URISyntaxException, IOException {
        System.setProperty("java.awt.headless", "false");

        URI homepage = new URI("http://localhost:8080/");

        Desktop.getDesktop().browse(homepage);
    }
}
