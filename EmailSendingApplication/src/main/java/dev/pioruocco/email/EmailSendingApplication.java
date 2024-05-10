package dev.pioruocco.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync //abilitiamo applicazione asincrona
public class EmailSendingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailSendingApplication.class, args);
    }

}
