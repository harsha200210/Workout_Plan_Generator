package lk.ijse.workoutplanbackend.controller;

import jakarta.validation.Valid;
import lk.ijse.workoutplanbackend.config.JwtFilter;
import lk.ijse.workoutplanbackend.dto.AuthDTO;
import lk.ijse.workoutplanbackend.dto.UserDTO;
import lk.ijse.workoutplanbackend.service.SignUpService;
import lk.ijse.workoutplanbackend.util.JwtUtil;
import lk.ijse.workoutplanbackend.util.ResponseUtil;
import lk.ijse.workoutplanbackend.util.VarList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class SignUpController {

    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @Autowired
    private JwtFilter jwtFilter;

    private final SignUpService signUpService;
    private final JwtUtil jwtUtil;

    //constructor injection
    public SignUpController(SignUpService signUpService, JwtUtil jwtUtil) {
        this.signUpService = signUpService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping(value = "/register")
    public ResponseEntity<ResponseUtil> registerUser(@RequestBody @Valid UserDTO userDTO) {
        try {
            int res = signUpService.saveUser(userDTO);
            logger.info("User registered successfully: {}", userDTO);
            switch (res) {
                case VarList.Created -> {
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setEmail(userDTO.getEmail());
                    authDTO.setToken(token);
                    jwtFilter.saveOrUpdateLoginToken(token);
                    logger.debug("Get Token");
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseUtil(VarList.Created, "Success", authDTO));
                }
                case VarList.Not_Acceptable -> {
                    logger.error("Email Already Used");
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseUtil(VarList.Not_Acceptable, "Email Already Used", null));
                }
                default -> {
                    logger.error("Error");
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseUtil(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
}
