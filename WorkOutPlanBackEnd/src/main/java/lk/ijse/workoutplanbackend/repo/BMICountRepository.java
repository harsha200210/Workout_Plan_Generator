package lk.ijse.workoutplanbackend.repo;

import lk.ijse.workoutplanbackend.entity.BMICount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BMICountRepository extends JpaRepository<BMICount,Long> {

    @Query("SELECT b.bmi, b.date FROM BMICount b WHERE b.user.userId = :userId ORDER BY b.date DESC limit 7")
    List<Object[]> findLast7BMIByUserId(@Param("userId") Long userId);
}
