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
public class Sets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long setId;
    private String types;
    private int sets;
    private int reps;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sets", cascade = CascadeType.ALL)
    private List<Plan> planList;
}
