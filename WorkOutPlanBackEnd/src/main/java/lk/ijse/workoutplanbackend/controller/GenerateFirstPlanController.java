package lk.ijse.workoutplanbackend.controller;

import lk.ijse.workoutplanbackend.dto.UserDTO;
import lk.ijse.workoutplanbackend.service.GenerateFirstPlanService;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/firstPlan")
public class GenerateFirstPlanController {

    private static final Logger logger = LoggerFactory.getLogger(GenerateFirstPlanController.class);

    @Autowired
    private GenerateFirstPlanService generateFirstPlanService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseUtil getFirstPlan(@RequestBody UserDTO userDTO) {
        logger.debug("Fetching day 1 plan from GenerateFirstPlanService");
        return generateFirstPlanService.generateWorkPlan(userDTO);
    }
}
