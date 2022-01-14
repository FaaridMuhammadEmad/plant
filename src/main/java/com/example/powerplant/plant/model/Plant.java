package com.example.powerplant.plant.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column
    @NotNull
    private String year;

    @Column
    @NotNull
    private String locationCode;

    @Column
    @NotNull
    private String plantName;

    @Column
    @NotNull
    private String genId;

    @Column
    @NotNull
    private String genStatus;

    @Column
    private Double genAnnualNet;

}
