package lk.ijse.workoutplanbackend.service.impl;

import lk.ijse.workoutplanbackend.dto.UserDTO;
import lk.ijse.workoutplanbackend.entity.*;
import lk.ijse.workoutplanbackend.repo.*;
import lk.ijse.workoutplanbackend.service.SignUpService;
import lk.ijse.workoutplanbackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public int saveUser(UserDTO userDTO) {
        if (userRepository.existsUsersByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userDTO.setPlanList(new ArrayList<>());
            userDTO.setPlanCount(0L);
//            userDTO.setRole("USER");
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.Created;
        }
    }
}
