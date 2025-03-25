package lk.ijse.workoutplanbackend.advisor;

import lk.ijse.workoutplanbackend.util.ResponseUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseUtil exceptionHandler(Exception exception) {
        return new ResponseUtil(500, "An error occurred: " , exception.getMessage());
    }
}
