package lk.ijse.workoutplanbackend.service.impl;

import lk.ijse.workoutplanbackend.dto.ExercisesDTO;
import lk.ijse.workoutplanbackend.dto.UserDTO;
import lk.ijse.workoutplanbackend.entity.Exercises;
import lk.ijse.workoutplanbackend.entity.LoginData;
import lk.ijse.workoutplanbackend.entity.User;
import lk.ijse.workoutplanbackend.repo.ExercisesRepository;
import lk.ijse.workoutplanbackend.repo.LoginDataRepository;
import lk.ijse.workoutplanbackend.repo.PlanRepository;
import lk.ijse.workoutplanbackend.repo.UserRepository;
import lk.ijse.workoutplanbackend.service.AdminService;
import lk.ijse.workoutplanbackend.util.JwtUtil;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExercisesRepository exercisesRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private LoginDataRepository loginDataRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseUtil getAllUsers(){
        List<User> users = userRepository.findAllByRole("USER");
        List<UserDTO> collect = new ArrayList<>();

        for(User user : users) {
            collect.add(new UserDTO(user.getFullName(), user.getGender(), user.getEmail(), user.getCountry(), user.getPlanCount()));
        }

        return new ResponseUtil(200, "All Users", collect);
    }

    @Override
    public ResponseUtil getUserCount() {
        long count = userRepository.countByRole("USER");
        return new ResponseUtil(200, "User Count", count);
    }

    @Override
    public ResponseUtil getExerciseCount() {
        long count = exercisesRepository.count();
        return new ResponseUtil(200, "Exercise Count", count);
    }

    @Override
    public ResponseUtil getPlanCount() {
        long count = planRepository.count();
        return new ResponseUtil(200, "Plan Count", count);
    }

    @Override
    public ResponseUtil saveExercise(ExercisesDTO exercisesDTO) {
        exercisesRepository.save(modelMapper.map(exercisesDTO, Exercises.class));
        return new ResponseUtil(201, "Exercises saved successfully" , null);
    }

    @Override
    public ResponseUtil getAllExercises() {
        List<Exercises> exercises = exercisesRepository.findAll();
        List<ExercisesDTO> collect = exercises.stream().map(e -> modelMapper.map(e, ExercisesDTO.class)).collect(Collectors.toList());
        return new ResponseUtil(200, "All Exercises", collect);
    }

    @Override
    public ResponseUtil updateExercises(ExercisesDTO exercisesDTO) {
        exercisesRepository.save(modelMapper.map(exercisesDTO, Exercises.class));
        return new ResponseUtil(200, "Exercises update successfully" , null);
    }

    @Override
    public ResponseUtil deleteExercises(Long exerciseId) {
        exercisesRepository.deleteById(exerciseId);
        return new ResponseUtil(200, "Exercises deleted successfully" , null);
    }

    @Override
    public ResponseUtil getExercisesByName(String name) {
        List<Exercises> exercisesByName = exercisesRepository.getExercisesByName(name);
        ExercisesDTO exercisesDTO = modelMapper.map(exercisesByName.get(0), ExercisesDTO.class);
        return new ResponseUtil(200, "Exercises by Name", exercisesDTO);
    }

    @Override
    public ResponseUtil getExercisesName() {
        List<Exercises> all = exercisesRepository.findAll();
        List<String> collect = all.stream().map(e -> e.getName()).collect(Collectors.toList());
        return new ResponseUtil(200, "Exercises Names", collect);
    }

    @Override
    public ResponseUtil checkPassword(String password) {
        LoginData referenceById = loginDataRepository.findById(1);
        String username = jwtUtil.getUsernameFromToken(referenceById.getToken());
        User admin = userRepository.getUsersByEmail(username);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(password, admin.getPassword());
        if(!matches) {
            return new ResponseUtil(401, "password incorrect" , false);
        }
        return new ResponseUtil(200, "matching password" , true);
    }

    @Override
    public ResponseUtil changePassword(String newPassword) {
        LoginData referenceById = loginDataRepository.findById(1);
        String username = jwtUtil.getUsernameFromToken(referenceById.getToken());
        User admin = userRepository.getUsersByEmail(username);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        admin.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(admin);

        return new ResponseUtil(200, "password changed successfully" , null);
    }

    @Override
    public ResponseUtil getAdminEmail() {
        LoginData referenceById = loginDataRepository.findById(1);
        String username = jwtUtil.getUsernameFromToken(referenceById.getToken());

        return new ResponseUtil(200, "userName" , username);
    }

}
