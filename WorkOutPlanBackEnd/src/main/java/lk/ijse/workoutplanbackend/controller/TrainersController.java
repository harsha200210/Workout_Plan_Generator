package lk.ijse.workoutplanbackend.controller;

import lk.ijse.workoutplanbackend.dto.TrainersDTO;
import lk.ijse.workoutplanbackend.entity.Trainers;
import lk.ijse.workoutplanbackend.service.TrainersService;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/trainers")
public class TrainersController {

    private static final Logger logger = LoggerFactory.getLogger(TrainersController.class);

    @Autowired
    private TrainersService trainersService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseUtil getAll(){
        logger.debug("Getting all trainers");
        return trainersService.getAllTrainers();
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseUtil saveTrainer(@RequestBody TrainersDTO trainersDTO){
        Trainers trainers = modelMapper.map(trainersDTO, Trainers.class);
        logger.debug("Saving trainer: {}", trainers);
        return trainersService.saveTrainer(trainers);
    }
}
