package lk.ijse.workoutplanbackend.repo;

import lk.ijse.workoutplanbackend.entity.Trainers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainersRepository extends JpaRepository<Trainers,Integer> {
}
