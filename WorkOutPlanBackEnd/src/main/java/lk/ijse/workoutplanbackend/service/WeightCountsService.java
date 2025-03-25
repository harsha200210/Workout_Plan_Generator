package lk.ijse.workoutplanbackend.service;

import lk.ijse.workoutplanbackend.dto.WeightCountsDTO;
import lk.ijse.workoutplanbackend.util.ResponseUtil;

public interface WeightCountsService {
    ResponseUtil saveWeightCounts(WeightCountsDTO weightCountsDTO);
    ResponseUtil getWeightCounts();
}
