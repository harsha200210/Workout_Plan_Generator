package lk.ijse.workoutplanbackend.repo;

import lk.ijse.workoutplanbackend.entity.Places;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacesRepository extends JpaRepository<Places,Integer> {
}
