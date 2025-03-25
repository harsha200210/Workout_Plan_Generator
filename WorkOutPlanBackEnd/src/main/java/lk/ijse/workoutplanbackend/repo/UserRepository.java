package lk.ijse.workoutplanbackend.repo;

import lk.ijse.workoutplanbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsUsersByEmail(String email);

    User getUsersByEmail(String email);

    List<User> findByEmail(String email);

    List<User> findAllByRole(String role);

    long countByRole(String role);
}
