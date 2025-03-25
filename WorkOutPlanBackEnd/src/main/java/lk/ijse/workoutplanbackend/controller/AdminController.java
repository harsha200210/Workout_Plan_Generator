package lk.ijse.workoutplanbackend.controller;

import lk.ijse.workoutplanbackend.dto.ExercisesDTO;
import lk.ijse.workoutplanbackend.service.AdminService;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @GetMapping("/allUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseUtil getAllUsers() {
        logger.debug("Get all users from AdminService");
        return adminService.getAllUsers();
    }

    @GetMapping("/userCount")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseUtil getUserCount() {
        logger.info("Get users count from AdminService");
        return adminService.getUserCount();
    }

    @GetMapping("/exerciseCount")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseUtil getExerciseCount() {
        logger.info("Get exercise count from AdminService");
        return adminService.getExerciseCount();
    }

    @GetMapping("/planCount")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseUtil getPlanCount() {
        logger.info("Get plan count from AdminService");
        return adminService.getPlanCount();
    }

    @GetMapping("/getAllExercise")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseUtil getAllExercises() {
        logger.debug("Get all exercise from AdminService");
        return adminService.getAllExercises();
    }

    @GetMapping("/getExerciseNames")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseUtil getExerciseNames() {
        logger.debug("Get exercise names from AdminService");
        return adminService.getExercisesName();
    }

    @PostMapping("/saveExercise")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseUtil saveExercise(@RequestBody ExercisesDTO exercisesDTO) {
        logger.info("Save exercise from AdminService");
        return adminService.saveExercise(exercisesDTO);
    }

    @PutMapping("/updateExercise")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseUtil updateExercise(@RequestBody ExercisesDTO exercisesDTO) {
        logger.info("Update exercise from AdminService");
        return adminService.updateExercises(exercisesDTO);
    }

    @DeleteMapping("/deleteExercise/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseUtil deleteExercise(@PathVariable("id") Long id) {
        logger.info("Attempting to delete exercise with ID: {}", id);
        try {
            ResponseUtil response = adminService.deleteExercises(id);
            logger.info("Deleted exercise successfully: {}", response);
            return response;
        } catch (Exception e) {
            logger.error("Failed to delete exercise with ID: {}", id, e);
            return new ResponseUtil(500, "Deletion failed", null);
        }
    }

    @PostMapping("/getExercise")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseUtil getExercise(@Param("name") String name) {
        logger.info("Get exercise from AdminService");
        return adminService.getExercisesByName(name);
    }

}
