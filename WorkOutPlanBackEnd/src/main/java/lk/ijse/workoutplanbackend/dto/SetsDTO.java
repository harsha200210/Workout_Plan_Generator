package lk.ijse.workoutplanbackend.dto;

import lk.ijse.workoutplanbackend.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SetsDTO {
    private Long setId;
    private String types;
    private int sets;
    private int reps;
    private List<Plan> planList;
}
