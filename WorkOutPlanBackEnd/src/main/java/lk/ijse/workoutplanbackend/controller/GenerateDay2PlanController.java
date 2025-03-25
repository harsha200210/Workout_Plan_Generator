package lk.ijse.workoutplanbackend.controller;

import lk.ijse.workoutplanbackend.dto.UserDTO;
import lk.ijse.workoutplanbackend.service.GenerateDay2PlanService;
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
@RequestMapping("/api/v1/2Day")
public class GenerateDay2PlanController {

    private static final Logger logger = LoggerFactory.getLogger(GenerateDay2PlanController.class);

    @Autowired
    private GenerateDay2PlanService generateDay2Plan;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseUtil generate(@RequestBody UserDTO userDTO) {
        logger.debug("Fetching day 2 plan from GenerateDay2PlanService");
        return generateDay2Plan.generateDay2Plan(userDTO);
    }
}
