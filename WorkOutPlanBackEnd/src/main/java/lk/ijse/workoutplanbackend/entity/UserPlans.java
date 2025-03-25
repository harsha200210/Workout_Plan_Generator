package lk.ijse.workoutplanbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPlans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String plan;

    @ManyToOne
    @JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_PLAN_USER"))
    private User user;

    public UserPlans(String plan, User user) {
        this.plan = plan;
        this.user = user;
    }
}
