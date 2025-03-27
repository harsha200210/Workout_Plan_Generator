package lk.ijse.workoutplanbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Exercises {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseId;
    @NotBlank(message = "Name is required")
    @Column(unique = true, nullable = false)
    private String name;
    @NotBlank(message = "Name is required")
    private String bodyPart;
    private String link;

    @ManyToOne
    @JoinColumn(name = "planDetailsId", foreignKey = @ForeignKey(name = "FK_PLANDETAILS"))
    private PlanDetails planDetails;
}
