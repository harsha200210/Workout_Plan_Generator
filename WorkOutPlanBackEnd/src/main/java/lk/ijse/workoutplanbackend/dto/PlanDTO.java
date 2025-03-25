package lk.ijse.workoutplanbackend.dto;

import lk.ijse.workoutplanbackend.entity.PlanDetails;
import lk.ijse.workoutplanbackend.entity.Sets;
import lk.ijse.workoutplanbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlanDTO {
    private Long planId;
    private User user;
    private Sets sets;
    private List<PlanDetails> planDetails;
}
