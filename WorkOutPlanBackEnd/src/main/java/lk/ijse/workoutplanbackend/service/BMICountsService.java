package lk.ijse.workoutplanbackend.service;

import lk.ijse.workoutplanbackend.dto.BMICountDTO;
import lk.ijse.workoutplanbackend.util.ResponseUtil;

public interface BMICountsService {

    ResponseUtil saveBMICounts(BMICountDTO bmiCountDTO);
    ResponseUtil getBMICounts();
}
