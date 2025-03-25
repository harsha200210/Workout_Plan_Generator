package lk.ijse.workoutplanbackend.controller;

import lk.ijse.workoutplanbackend.dto.BMICountDTO;
import lk.ijse.workoutplanbackend.service.BMICountsService;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bmi")
public class BMICountsController {

    private static final Logger logger = LoggerFactory.getLogger(BMICountsController.class);

    @Autowired
    private BMICountsService bmiCountsService;

    @GetMapping("/getBmiCounts")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseUtil getBMICounts() {
        logger.debug("Fetching BMI counts from BMICountsService");
        ResponseUtil response = bmiCountsService.getBMICounts();
        logger.info("Fetched BMI counts successfully: {}", response);
        return response;
    }

    @PostMapping("/saveBmiCounts")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseUtil saveBMICounts(@RequestBody BMICountDTO bmiCountDTO) {
        logger.info("Saving BMI counts: {}", bmiCountDTO);
        try {
            ResponseUtil response = bmiCountsService.saveBMICounts(bmiCountDTO);
            logger.info("Saved BMI counts successfully: {}", response);
            return response;
        } catch (Exception e) {
            logger.error("Error while saving BMI counts: {}", bmiCountDTO, e);
            return new ResponseUtil(500, "BMI count save failed", null);
        }
    }
}
