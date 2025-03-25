package lk.ijse.workoutplanbackend.repo;

import lk.ijse.workoutplanbackend.entity.PlanDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanDetailsRepository extends JpaRepository<PlanDetails,Integer> {
}
