package lk.ijse.workoutplanbackend.service.impl;

import lk.ijse.workoutplanbackend.entity.Places;
import lk.ijse.workoutplanbackend.repo.PlacesRepository;
import lk.ijse.workoutplanbackend.service.PlacesService;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlacesServiceImpl implements PlacesService {

    @Autowired
    private PlacesRepository placesRepository;

    @Override
    public ResponseUtil savePlace(Places places) {
        placesRepository.save(places);
        return new ResponseUtil(201,"Place Save Successfully", places);
    }

    @Override
    public ResponseUtil getAllPlaces() {
        return new ResponseUtil(200,"All Places", placesRepository.findAll());
    }
}
