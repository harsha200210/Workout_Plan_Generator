package lk.ijse.workoutplanbackend.repo;

import lk.ijse.workoutplanbackend.entity.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDataRepository extends JpaRepository<LoginData,Integer> {
    boolean getLoginDataById(int id);

    LoginData findById(int id);
}
