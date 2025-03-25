package lk.ijse.workoutplanbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeightCountsDTO {
    private Double weight;
    private Date date;
}
