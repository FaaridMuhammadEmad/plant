package com.example.powerplant.plant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PercentageValueDto {
    private Long id;
    private String plantName;
    private Double genAnnualNet;
    private Double percentageValue;
    private String locationCode;
}
