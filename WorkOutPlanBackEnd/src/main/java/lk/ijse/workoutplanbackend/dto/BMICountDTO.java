package lk.ijse.workoutplanbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BMICountDTO {
    private Double bmi;
    private Date date;
}
