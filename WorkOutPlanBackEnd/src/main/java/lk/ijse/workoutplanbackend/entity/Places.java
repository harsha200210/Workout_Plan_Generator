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
public class Places {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Location is required")
    private String location;
    private Long tel;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;
}
