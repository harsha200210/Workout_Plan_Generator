package lk.ijse.workoutplanbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[A-Za-z]+(?: [A-Za-z]+)*$", message = "Name must contain only letters and spaces")
    private String name;
    private String location;
    private String experience;
    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private Long tel;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;
}
