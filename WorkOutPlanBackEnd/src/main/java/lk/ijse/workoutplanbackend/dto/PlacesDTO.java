package lk.ijse.workoutplanbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlacesDTO {
    private String name;
    private String location;
    private Long tel;
    private String email;
}
