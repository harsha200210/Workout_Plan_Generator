package lk.ijse.workoutplanbackend.service;

import lk.ijse.workoutplanbackend.entity.Trainers;
import lk.ijse.workoutplanbackend.util.ResponseUtil;

public interface TrainersService {

    ResponseUtil getAllTrainers();
    ResponseUtil saveTrainer(Trainers trainers);
}
