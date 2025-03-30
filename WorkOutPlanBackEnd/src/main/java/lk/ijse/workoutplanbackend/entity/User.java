package lk.ijse.workoutplanbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    //@Pattern(regexp = "^[A-Za-z]+(?: [A-Za-z]+)*$", message = "Name must contain only letters and spaces")
    private String fullName;
    private String Gender;
    @Column(unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;
    private String password;
    private String country;
    private String role;
    private String nowBodyType;
    private String targetBodyType;
    private double weight;
    private double targetWeight;
    private int workOutTime;
    private Long planCount;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Plan> planList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<WeightCounts> weightCounts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPlans> userPlans;

}
