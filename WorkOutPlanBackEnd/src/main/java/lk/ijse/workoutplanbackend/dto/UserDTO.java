package lk.ijse.workoutplanbackend.dto;

import lk.ijse.workoutplanbackend.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long userId;
    private String fullName;
    private String gender;
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
    private List<Plan> planList;

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDTO(String nowBodyType, double weight, int workOutTime) {
        this.nowBodyType = nowBodyType;
        this.weight = weight;
        this.workOutTime = workOutTime;
    }

    public UserDTO(String fullName, String gender, String email, String password, String country, String role) {
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.country = country;
        this.role = role;
    }

    public UserDTO(String nowBodyType, String targetBodyType, double weight, double targetWeight, int workOutTime) {
        this.nowBodyType = nowBodyType;
        this.targetBodyType = targetBodyType;
        this.weight = weight;
        this.targetWeight = targetWeight;
        this.workOutTime = workOutTime;
    }

    public UserDTO(String fullName, String gender, String email, String country, Long planCount) {
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.country = country;
        this.planCount = planCount;
    }
}
