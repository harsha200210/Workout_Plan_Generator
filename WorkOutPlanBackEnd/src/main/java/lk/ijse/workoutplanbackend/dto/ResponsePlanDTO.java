package lk.ijse.workoutplanbackend.dto;

import lk.ijse.workoutplanbackend.entity.Exercises;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponsePlanDTO {
    private String[] exercises;
    private List<Exercises> exercisesList;
}
