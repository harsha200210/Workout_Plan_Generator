package lk.ijse.workoutplanbackend.service;

import lk.ijse.workoutplanbackend.dto.UserDTO;
import lk.ijse.workoutplanbackend.util.ResponseUtil;

public interface GenerateDay2PlanService {

    ResponseUtil generateDay2Plan(UserDTO userDTO);
}
