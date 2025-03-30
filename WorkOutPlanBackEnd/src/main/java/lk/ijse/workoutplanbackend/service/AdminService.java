package lk.ijse.workoutplanbackend.service;

import lk.ijse.workoutplanbackend.dto.ExercisesDTO;
import lk.ijse.workoutplanbackend.util.ResponseUtil;

public interface AdminService {

    ResponseUtil getAllUsers();
    ResponseUtil getUserCount();
    ResponseUtil getExerciseCount();
    ResponseUtil getPlanCount();

    ResponseUtil saveExercise(ExercisesDTO exercisesDTO);
    ResponseUtil getAllExercises();
    ResponseUtil updateExercises(ExercisesDTO exercisesDTO);
    ResponseUtil deleteExercises(Long exerciseId);
    ResponseUtil getExercisesByName(String name);
    ResponseUtil getExercisesName();

    ResponseUtil checkPassword(String password);
    ResponseUtil changePassword(String newPassword);
    ResponseUtil getAdminEmail();
}
