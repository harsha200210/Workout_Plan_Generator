package lk.ijse.workoutplanbackend.service;

import lk.ijse.workoutplanbackend.dto.UserDTO;

public interface SignUpService {

    int saveUser(UserDTO userDTO);
}
