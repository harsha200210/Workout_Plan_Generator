package lk.ijse.workoutplanbackend.service;

import lk.ijse.workoutplanbackend.dto.UserDTO;
import lk.ijse.workoutplanbackend.util.ResponseUtil;

public interface GenerateFirstPlanService {

    ResponseUtil generateWorkPlan(UserDTO userDTO);
}
