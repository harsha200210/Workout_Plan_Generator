package lk.ijse.workoutplanbackend.controller;

import lk.ijse.workoutplanbackend.config.JwtFilter;
import lk.ijse.workoutplanbackend.dto.AuthDTO;
import lk.ijse.workoutplanbackend.dto.UserDTO;
import lk.ijse.workoutplanbackend.service.LoginService;
import lk.ijse.workoutplanbackend.util.JwtUtil;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import lk.ijse.workoutplanbackend.util.VarList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(GenerateDay2PlanController.class);

    @Autowired
    private  JwtFilter jwtFilter;

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final LoginService loginService;
    private final ResponseUtil responseUtil;

    //constructor injection
    public LoginController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, LoginService loginService, ResponseUtil responseUtil) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.loginService = loginService;
        this.responseUtil = responseUtil;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseUtil> authenticate(@RequestBody UserDTO userDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        } catch (Exception e) {
            logger.error("Invalid Credentials : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseUtil(VarList.Unauthorized, "Invalid Credentials", e.getMessage()));
        }

        UserDTO loadedUser = loginService.loadUserDetailsByUsername(userDTO.getEmail());
        if (loadedUser == null) {
            logger.error("Authorization Failure! Please Try Again");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseUtil(VarList.Conflict, "Authorization Failure! Please Try Again", null));
        }

        String token = jwtUtil.generateToken(loadedUser);
        if (token == null || token.isEmpty()) {
            logger.error("Authorization Failure! Please Try Again");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseUtil(VarList.Conflict, "Authorization Failure! Please Try Again", null));
        }

        AuthDTO authDTO = new AuthDTO();
        authDTO.setEmail(loadedUser.getEmail());
        authDTO.setToken(token);

        jwtFilter.saveOrUpdateLoginToken(token);

        logger.debug("Authorization successfully");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseUtil(VarList.Created, loadedUser.getRole(), authDTO));
    }

    @GetMapping("/planCount")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseUtil getPlanCount() {
        logger.info("Get plan count from loginService");
        return loginService.getPlanCount(jwtFilter.getUserName());
    }

    @GetMapping("/nowWeight")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseUtil getNowWeight(){
        logger.info("Get now weight from loginService");
        return loginService.getNowWeight(jwtFilter.getUserName());
    }

    @PostMapping("/google-login")
    public ResponseEntity<ResponseUtil> googleLogin(@RequestBody UserDTO userDTO) {
        UserDTO dto = loginService.loginToGoogle(userDTO);

        String token = jwtUtil.generateToken(dto);

        if (token == null || token.isEmpty()) {
            logger.error("Authorization Failure! Please Try Again");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseUtil(VarList.Conflict, "Authorization Failure! Please Try Again", null));
        }

        AuthDTO authDTO = new AuthDTO();
        authDTO.setEmail(dto.getEmail());
        authDTO.setToken(token);

        jwtFilter.saveOrUpdateLoginToken(token);

        logger.debug("Google Authorization successfully");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseUtil(VarList.Created, dto.getRole(), authDTO));
    }

}
