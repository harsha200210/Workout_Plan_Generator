package lk.ijse.workoutplanbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PlanDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "planId", foreignKey = @ForeignKey(name = "FK_PLAN"))
    private Plan plan;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "planDetails", cascade = CascadeType.MERGE)
    private List<Exercises> exercises;

    public PlanDetails(Plan plan, List<Exercises> exercises) {
        this.plan = plan;
        this.exercises = exercises;
    }
}
