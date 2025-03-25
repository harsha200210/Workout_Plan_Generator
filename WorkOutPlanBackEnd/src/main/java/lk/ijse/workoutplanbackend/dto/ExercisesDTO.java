package lk.ijse.workoutplanbackend.dto;

import lk.ijse.workoutplanbackend.entity.PlanDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExercisesDTO {
    private Long exerciseId;
    private String name;
    private String bodyPart;
    private String link;
    private PlanDetails planDetails;


}
