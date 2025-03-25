package lk.ijse.workoutplanbackend.service.impl;

import lk.ijse.workoutplanbackend.entity.LoginData;
import lk.ijse.workoutplanbackend.entity.User;
import lk.ijse.workoutplanbackend.repo.LoginDataRepository;
import lk.ijse.workoutplanbackend.repo.UserPlanRepository;
import lk.ijse.workoutplanbackend.repo.UserRepository;
import lk.ijse.workoutplanbackend.service.SchedulesService;
import lk.ijse.workoutplanbackend.util.CompressionUtil;
import lk.ijse.workoutplanbackend.util.JwtUtil;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulesServiceImpl implements SchedulesService {

    @Autowired
    private UserPlanRepository userPlanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginDataRepository loginDataRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CompressionUtil compressionUtil;

    @Override
    public ResponseUtil getSchedules(){
        List<String> plans = userPlanRepository.findLast2PlansByUserId(getUserName().getUserId());
        if(plans.isEmpty()){
            return new ResponseUtil(404, "No schedules found", null);
        }

        List<String[]> schedules = new ArrayList<>();
        try {
            for(String plan : plans){
                String decompress = compressionUtil.decompress(plan);
                String[] schedule = decompress.split(",");
                schedules.add(schedule);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ResponseUtil(200, "Schedules fetched successfully", schedules);
    }

    public User getUserName() {
        LoginData referenceById = loginDataRepository.findById(1);
        String username = jwtUtil.getUsernameFromToken(referenceById.getToken());
        return userRepository.getUsersByEmail(username);
    }
}
