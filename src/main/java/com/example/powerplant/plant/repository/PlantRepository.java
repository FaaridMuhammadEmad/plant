package com.example.powerplant.plant.repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.powerplant.plant.model.Plant;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant,Long> {

    Plant findOneById(Long id);

    Page<Plant> findAllByLocationCode(String location,Pageable pageable);
    Page<Plant> findAllByGenAnnualNetIsNotNull(Pageable topTen);

    @Query(value="SELECT * FROM plant where location_code=?1",nativeQuery = true)
    List<Plant> findAllPlantsByLocationCode(String locationCode);

    @Query(value="SELECT SUM(gen_annual_net) FROM plant",nativeQuery = true)
    Double findSumOfAll();

    @Query(value = "SELECT SUM(gen_annual_net),plant_name FROM plant where location_code=?1 GROUP BY plant_name",nativeQuery = true)
    Object[] findSumGroupByName(String locationCode);
}
