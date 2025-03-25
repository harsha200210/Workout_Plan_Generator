package lk.ijse.workoutplanbackend.repo;

import lk.ijse.workoutplanbackend.entity.Sets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SetsRepository extends JpaRepository<Sets,Integer> {
    List<Sets> getSetsByTypes(String types);

    Sets findBySetId(Long setId);
}
