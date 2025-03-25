package lk.ijse.workoutplanbackend.controller;

import lk.ijse.workoutplanbackend.dto.PlacesDTO;
import lk.ijse.workoutplanbackend.entity.Places;
import lk.ijse.workoutplanbackend.service.PlacesService;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/places")
public class PlacesController {

    private static final Logger logger = LoggerFactory.getLogger(PlacesController.class);

    @Autowired
    private PlacesService placesService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseUtil getAll() {
        logger.debug("Get plan count from loginService");
        return placesService.getAllPlaces();
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseUtil savePlace(@RequestBody PlacesDTO placesDTO) {
        Places places = modelMapper.map(placesDTO, Places.class);
        logger.debug("Save place to loginService: {}", places);
        return placesService.savePlace(places);
    }
}
