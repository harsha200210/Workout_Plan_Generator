package lk.ijse.workoutplanbackend.service.impl;

import lk.ijse.workoutplanbackend.dto.UserDTO;
import lk.ijse.workoutplanbackend.entity.User;
import lk.ijse.workoutplanbackend.repo.UserRepository;
import lk.ijse.workoutplanbackend.service.LoginService;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class LoginServiceImpl implements LoginService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public static User user;

    @Override
    public ResponseUtil login(String email, String password) {
        if (userRepository.existsUsersByEmail(email)){
            User usersByEmail = userRepository.getUsersByEmail(email);
            if (usersByEmail.getPassword().equals(password)){
                user = usersByEmail;
                return new ResponseUtil(200, "Login successful", true);
            } else {
                return new ResponseUtil(401, "Incorrect password", false);
            }
        } else {
            return new ResponseUtil(404, "User not found", false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUsersByEmail(email);

        String password;
        if (user.getPassword() == null) {
            password = "";
        } else {
            password = user.getPassword();
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), password, getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUsersByEmail(username);
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public ResponseUtil getPlanCount(String username) {
        User users = userRepository.getUsersByEmail(username);
        return new ResponseUtil(200, "Plan count", users.getPlanCount());
    }

    @Override
    public UserDTO loginToGoogle(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);

        if (userRepository.existsUsersByEmail(user.getEmail())){
            User usersByEmail = userRepository.getUsersByEmail(user.getEmail());
            return modelMapper.map(usersByEmail,UserDTO.class);
        } else {
            user.setPlanCount(0L);
            user.setRole("USER");
            userRepository.save(user);
            return modelMapper.map(user,UserDTO.class);
        }
    }

    @Override
    public ResponseUtil getNowWeight(String email) {
        User user = userRepository.getUsersByEmail(email);
        return new ResponseUtil(200, "Now weight", user.getWeight());
    }
}
