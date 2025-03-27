package lk.ijse.workoutplanbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BMICount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bmiId;
    @Pattern(regexp = "^\\d*\\.?\\d+$", message = "Invalid BMI")
    private Double bmi;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_BMI_USER"))
    private User user;
}
