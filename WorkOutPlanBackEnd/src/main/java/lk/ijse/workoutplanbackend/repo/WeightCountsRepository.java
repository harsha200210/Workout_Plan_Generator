package lk.ijse.workoutplanbackend.repo;

import lk.ijse.workoutplanbackend.entity.WeightCounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeightCountsRepository extends JpaRepository<WeightCounts,Long> {

    @Query("SELECT w.weight, w.date FROM WeightCounts w WHERE w.user.userId = :userId ORDER BY w.date DESC limit 7")
    List<Object[]> findLast7WeightsByUserId(@Param("userId") Long userId);
}
