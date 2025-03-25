package lk.ijse.workoutplanbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trainers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainerId;
    private String name;
    private String location;
    private String experience;
    private Long tel;
    private String email;
}
