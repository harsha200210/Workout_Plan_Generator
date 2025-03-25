package lk.ijse.workoutplanbackend.service.impl;

import lk.ijse.workoutplanbackend.dto.BMICountDTO;
import lk.ijse.workoutplanbackend.entity.BMICount;
import lk.ijse.workoutplanbackend.entity.LoginData;
import lk.ijse.workoutplanbackend.entity.User;
import lk.ijse.workoutplanbackend.repo.BMICountRepository;
import lk.ijse.workoutplanbackend.repo.LoginDataRepository;
import lk.ijse.workoutplanbackend.repo.UserRepository;
import lk.ijse.workoutplanbackend.service.BMICountsService;
import lk.ijse.workoutplanbackend.util.JwtUtil;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class BMICountsServiceImpl implements BMICountsService {

    @Autowired
    private BMICountRepository bmiCountRepository;

    @Autowired
    private LoginDataRepository loginDataRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseUtil saveBMICounts(BMICountDTO bmiCountDTO) {
        BMICount bmiCount = modelMapper.map(bmiCountDTO, BMICount.class);
        bmiCount.setUser(getUserName());
        bmiCountRepository.save(bmiCount);
        return new ResponseUtil(201, "BMI Count updated", null);
    }

    @Override
    public ResponseUtil getBMICounts() {
        List<Object[]> list = bmiCountRepository.findLast7BMIByUserId(getUserName().getUserId());
        List<BMICountDTO> counts = new ArrayList<>();

        for (Object[] obj : list) {
            counts.add(new BMICountDTO((Double) obj[0], (Date) obj[1]));
        }
        return new ResponseUtil(200, "BMI Counts fetched", counts);
    }

    public User getUserName() {
        LoginData referenceById = loginDataRepository.findById(1);
        String username = jwtUtil.getUsernameFromToken(referenceById.getToken());
        return userRepository.getUsersByEmail(username);
    }
}
