package lk.ijse.workoutplanbackend.entity;

import jakarta.persistence.*;
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
    private String fullName;
    private String Gender;
    @Column(unique = true)
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
