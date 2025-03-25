package lk.ijse.workoutplanbackend.service.impl;

import lk.ijse.workoutplanbackend.dto.WeightCountsDTO;
import lk.ijse.workoutplanbackend.entity.LoginData;
import lk.ijse.workoutplanbackend.entity.User;
import lk.ijse.workoutplanbackend.entity.WeightCounts;
import lk.ijse.workoutplanbackend.repo.LoginDataRepository;
import lk.ijse.workoutplanbackend.repo.UserRepository;
import lk.ijse.workoutplanbackend.repo.WeightCountsRepository;
import lk.ijse.workoutplanbackend.service.WeightCountsService;
import lk.ijse.workoutplanbackend.util.JwtUtil;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeightCountsServiceImpl implements WeightCountsService {

    @Autowired
    private WeightCountsRepository weightCountsRepository;

    @Autowired
    private LoginDataRepository loginDataRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    @Transactional
    public ResponseUtil saveWeightCounts(WeightCountsDTO weightCountsDTO) {
        WeightCounts weightCounts = modelMapper.map(weightCountsDTO, WeightCounts.class);
        User user = getUserName();
        weightCounts.setUser(user);
        user.setWeight(weightCountsDTO.getWeight());
        weightCountsRepository.save(weightCounts);
        userRepository.save(user);
        return new ResponseUtil(201, "WeightCounts updated successfully", null);
    }

    @Override
    public ResponseUtil getWeightCounts() {
        User user = getUserName();
        List<Object[]> last7WeightsByUserId = weightCountsRepository.findLast7WeightsByUserId(user.getUserId());
        List<WeightCountsDTO> weightCountsDTOS = new ArrayList<>();

        for (Object[] objects : last7WeightsByUserId) {
            weightCountsDTOS.add(new WeightCountsDTO((Double) objects[0], (Date) objects[1]));
        }

        return new ResponseUtil(200, "Get WeightCounts", weightCountsDTOS);
    }

    public User getUserName() {
        LoginData referenceById = loginDataRepository.findById(1);
        String username = jwtUtil.getUsernameFromToken(referenceById.getToken());
        return userRepository.getUsersByEmail(username);
    }
}
