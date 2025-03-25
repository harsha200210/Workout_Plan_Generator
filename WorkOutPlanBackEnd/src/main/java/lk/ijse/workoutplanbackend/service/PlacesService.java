package lk.ijse.workoutplanbackend.service;

import lk.ijse.workoutplanbackend.entity.Places;
import lk.ijse.workoutplanbackend.util.ResponseUtil;

public interface PlacesService {

    ResponseUtil savePlace(Places places);
    ResponseUtil getAllPlaces();
}
