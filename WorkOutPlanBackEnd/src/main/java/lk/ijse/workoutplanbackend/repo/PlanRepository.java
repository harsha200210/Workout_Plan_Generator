package lk.ijse.workoutplanbackend.repo;

import lk.ijse.workoutplanbackend.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan,Integer> {
}
