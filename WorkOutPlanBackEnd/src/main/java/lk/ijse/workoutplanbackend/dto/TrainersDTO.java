package lk.ijse.workoutplanbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainersDTO {
    private String name;
    private String location;
    private String experience;
    private Long tel;
    private String email;
}
