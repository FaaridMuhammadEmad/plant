package com.example.powerplant.plant.repository;

import java.util.List;
import com.example.powerplant.plant.model.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlantRepository extends JpaRepository<Plant,Long> {

    Plant findOneById(Long id);
    Page<Plant> findAllByLocationCode(String location,Pageable pageable);
    Page<Plant> findAllByGenAnnualNetIsNotNull(Pageable topTen);

    @Query(value="SELECT * FROM plant where location_code=?1",nativeQuery = true)
    List<Plant> findAllPlantsByLocationCode(String locationCode);

    @Query(value="SELECT SUM(gen_annual_net) FROM plant",nativeQuery = true)
    Double findSumOfAll();
}
