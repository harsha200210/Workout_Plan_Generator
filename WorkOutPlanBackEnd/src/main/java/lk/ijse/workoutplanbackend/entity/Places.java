package lk.ijse.workoutplanbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Places {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;
    private String name;
    private String location;
    private Long tel;
    private String email;
}
