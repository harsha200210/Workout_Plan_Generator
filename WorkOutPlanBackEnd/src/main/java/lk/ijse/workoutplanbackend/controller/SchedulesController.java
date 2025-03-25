package lk.ijse.workoutplanbackend.controller;

import lk.ijse.workoutplanbackend.service.SchedulesService;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/schedules")
public class SchedulesController {

    private static final Logger logger = LoggerFactory.getLogger(SchedulesController.class);

    @Autowired
    private SchedulesService schedulesService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseUtil getSchedules() {
        logger.debug("Get schedules");
        return schedulesService.getSchedules();
    }

}
