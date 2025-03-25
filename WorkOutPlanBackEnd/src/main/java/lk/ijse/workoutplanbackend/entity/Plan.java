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
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @ManyToOne
    @JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_USER"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "setId", foreignKey = @ForeignKey(name = "FK_SET"))
    private Sets sets;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "plan", cascade = CascadeType.ALL)
    private List<PlanDetails> planDetails;


}
