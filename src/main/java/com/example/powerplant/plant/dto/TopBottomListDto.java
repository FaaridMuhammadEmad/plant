package com.example.powerplant.plant.dto;

import com.example.powerplant.plant.model.Plant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TopBottomListDto {
    List<Plant> topList;
    List<Plant> bottomList;
}
