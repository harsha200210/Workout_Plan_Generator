package lk.ijse.workoutplanbackend.service;

import lk.ijse.workoutplanbackend.dto.UserDTO;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface LoginService {

    ResponseUtil login(String email, String password);
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
    UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException;
    ResponseUtil getPlanCount(String username);
    UserDTO loginToGoogle(UserDTO userDTO);
    ResponseUtil getNowWeight(String email);
}
