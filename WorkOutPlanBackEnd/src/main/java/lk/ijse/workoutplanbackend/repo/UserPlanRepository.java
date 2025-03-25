package lk.ijse.workoutplanbackend.repo;

import lk.ijse.workoutplanbackend.entity.UserPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPlanRepository extends JpaRepository<UserPlans, Long> {

    @Query("SELECT u.plan FROM UserPlans u WHERE u.user.userId = :userId ORDER BY u.id DESC limit 2")
    List<String> findLast2PlansByUserId(@Param("userId") Long userId);
}
