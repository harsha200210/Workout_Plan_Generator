package lk.ijse.workoutplanbackend.controller;

import lk.ijse.workoutplanbackend.dto.UserDTO;
import lk.ijse.workoutplanbackend.service.GenerateDay3PlanService;
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
@RequestMapping("api/v1/3Day")
public class GenerateDay3PlanController {

    private static final Logger logger = LoggerFactory.getLogger(GenerateDay3PlanController.class);

    @Autowired
    private GenerateDay3PlanService generateDay3PlanService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseUtil generate3Day(@RequestBody UserDTO userDTO) {
        logger.debug("Fetching day 3 plan from GenerateDay3PlanService");
        return generateDay3PlanService.generateDay3Plan(userDTO);
    }

}
