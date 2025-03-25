package lk.ijse.workoutplanbackend.dto;

import lk.ijse.workoutplanbackend.entity.Exercises;
import lk.ijse.workoutplanbackend.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlanDetailsDTO {
    private Long id;
    private Plan plan;
    private List<Exercises> exercises;
}
