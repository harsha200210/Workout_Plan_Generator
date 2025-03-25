package lk.ijse.workoutplanbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WorkOutPlanBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkOutPlanBackEndApplication.class, args);
    }

}
