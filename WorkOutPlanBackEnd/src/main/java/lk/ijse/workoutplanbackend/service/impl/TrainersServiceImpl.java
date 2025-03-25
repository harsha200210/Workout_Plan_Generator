package lk.ijse.workoutplanbackend.service.impl;

import lk.ijse.workoutplanbackend.entity.Trainers;
import lk.ijse.workoutplanbackend.repo.TrainersRepository;
import lk.ijse.workoutplanbackend.service.TrainersService;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainersServiceImpl implements TrainersService {

    @Autowired
    private TrainersRepository trainersRepository;

    @Override
    public ResponseUtil getAllTrainers() {
        List<Trainers> all = trainersRepository.findAll();
        return new ResponseUtil(200,"All Trainers",all);
    }

    @Override
    public ResponseUtil saveTrainer(Trainers trainers) {
        trainersRepository.save(trainers);
        return new ResponseUtil(201,"Trainer saved successfully",trainers);
    }
}
