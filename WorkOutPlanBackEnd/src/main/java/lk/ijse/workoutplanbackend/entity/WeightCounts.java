package lk.ijse.workoutplanbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeightCounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long weightId;
    private Double weight;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_WEIGHT_USER"))
    private User user;
}
