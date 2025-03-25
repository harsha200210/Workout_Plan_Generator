package lk.ijse.workoutplanbackend.repo;

import lk.ijse.workoutplanbackend.entity.Exercises;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExercisesRepository extends JpaRepository<Exercises,Long> {
    List<Exercises> findExercisesByBodyPart(String bodyPart);

    List<Exercises> findExercisesByName(String name);

    List<Exercises> getExercisesByName(String name);

    long countByExerciseId(Long exerciseId);
}
