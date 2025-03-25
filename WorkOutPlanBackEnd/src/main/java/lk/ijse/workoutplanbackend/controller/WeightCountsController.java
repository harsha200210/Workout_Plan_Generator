package lk.ijse.workoutplanbackend.controller;

import lk.ijse.workoutplanbackend.dto.WeightCountsDTO;
import lk.ijse.workoutplanbackend.service.WeightCountsService;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/weightCounts")
public class WeightCountsController {

    private static final Logger logger = LoggerFactory.getLogger(WeightCountsController.class);

    @Autowired
    private WeightCountsService weightCountsService;

    @GetMapping("/getWeight")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseUtil getWeightCountData() {
        logger.debug("Fetching weight counts data");
        return weightCountsService.getWeightCounts();
    }

    @PostMapping("/saveWeight")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseUtil saveWeightCountData(@RequestBody WeightCountsDTO weightCountsDTO) {
        logger.debug("Saving weight counts data: {}", weightCountsDTO);
        return weightCountsService.saveWeightCounts(weightCountsDTO);
    }
}
