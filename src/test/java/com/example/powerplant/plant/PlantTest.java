package com.example.powerplant.plant;

import com.example.powerplant.objectFactoryUtils.ObjectFactoryUtil;
import com.example.powerplant.plant.repository.PlantRepository;
import com.example.powerplant.plant.service.implementation.PlantServiceImpl;
import com.example.powerplant.user.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlantTest {

    @Autowired
    PlantServiceImpl plantService;

    @MockBean
    PlantRepository plantRepository;

    @MockBean
    UserRepository userRepository;

    @Test
    @DisplayName("Getting top and bottom plants")
    public void getTopAndBottomPlants(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
       when(plantRepository.findAllByGenAnnualNetIsNotNull(ObjectFactoryUtil.topPageable())).thenReturn(ObjectFactoryUtil.pagePlantList());
       when(plantRepository.findAllByGenAnnualNetIsNotNull(ObjectFactoryUtil.bottomPageable())).thenReturn(ObjectFactoryUtil.pagePlantList());
        assertEquals(200, plantService.getTopAndBottomPlants(()->ObjectFactoryUtil.validEmail,5).getCode());
    }

    @Test
    @DisplayName("Getting top and bottom plants with unauthorized user")
    public void getTopAndBottomPlantsWithUnauthorizedUser(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findAllByGenAnnualNetIsNotNull(ObjectFactoryUtil.topPageable())).thenReturn(ObjectFactoryUtil.pagePlantList());
        when(plantRepository.findAllByGenAnnualNetIsNotNull(ObjectFactoryUtil.bottomPageable())).thenReturn(ObjectFactoryUtil.pagePlantList());
        assertEquals(401, plantService.getTopAndBottomPlants(()->ObjectFactoryUtil.invalidEmail,5).getCode());
    }

    @Test
    @DisplayName("Getting top and bottom plants with invalid input")
    public void getTopAndBottomPlantsWithInvalidInput(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findAllByGenAnnualNetIsNotNull(ObjectFactoryUtil.topPageable())).thenReturn(ObjectFactoryUtil.pagePlantList());
        when(plantRepository.findAllByGenAnnualNetIsNotNull(ObjectFactoryUtil.bottomPageable())).thenReturn(ObjectFactoryUtil.pagePlantList());
        assertEquals(400, plantService.getTopAndBottomPlants(()->ObjectFactoryUtil.invalidEmail,-5).getCode());
    }

    @Test
    @DisplayName("Get filter plants by location")
    public void getFilterPlantsByLocation(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findAllPlantsByLocationCode(ObjectFactoryUtil.locationCode)).thenReturn(ObjectFactoryUtil.plantList());
        when(plantRepository.findAllByGenAnnualNetIsNotNull(ObjectFactoryUtil.bottomPageable())).thenReturn(ObjectFactoryUtil.pagePlantList());
        assertEquals(200, plantService.getFilterPlantsByLocation(()->ObjectFactoryUtil.validEmail,ObjectFactoryUtil.locationCode).getCode());
    }

    @Test
    @DisplayName("Get filter plants by location with unauthorized user")
    public void getFilterPlantsByLocationWithUnauthorizedUser(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findAllPlantsByLocationCode(ObjectFactoryUtil.locationCode)).thenReturn(ObjectFactoryUtil.plantList());
        when(plantRepository.findAllByGenAnnualNetIsNotNull(ObjectFactoryUtil.bottomPageable())).thenReturn(ObjectFactoryUtil.pagePlantList());
        assertEquals(401, plantService.getFilterPlantsByLocation(()->ObjectFactoryUtil.invalidEmail,ObjectFactoryUtil.locationCode).getCode());
    }

    @Test
    @DisplayName("Get filter plants by invalid input")
    public void getFilterPlantsByInvalidInput(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findAllPlantsByLocationCode(ObjectFactoryUtil.locationCode)).thenReturn(ObjectFactoryUtil.plantList());
        when(plantRepository.findAllByGenAnnualNetIsNotNull(ObjectFactoryUtil.bottomPageable())).thenReturn(ObjectFactoryUtil.pagePlantList());
        assertEquals(400, plantService.getFilterPlantsByLocation(()->ObjectFactoryUtil.validEmail,null).getCode());
    }

    @Test
    @DisplayName("Get filter plants by invalid location")
    public void getFilterPlantsByInvalidLocation(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findAllPlantsByLocationCode(ObjectFactoryUtil.locationCode)).thenReturn(ObjectFactoryUtil.plantList());
        when(plantRepository.findAllByGenAnnualNetIsNotNull(ObjectFactoryUtil.bottomPageable())).thenReturn(ObjectFactoryUtil.pagePlantList());
        assertEquals(404, plantService.getFilterPlantsByLocation(()->ObjectFactoryUtil.validEmail,ObjectFactoryUtil.invalidLocationCode).getCode());
    }

    @Test
    @DisplayName("Get plants by location with pagination")
    public void getPlantsByLocationWithPagination(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findAllByLocationCode(ObjectFactoryUtil.locationCode,ObjectFactoryUtil.pageable())).thenReturn(ObjectFactoryUtil.pagePlantList());
        assertEquals(200, plantService.getPlantsByLocationWithPagination(()->ObjectFactoryUtil.validEmail,5,0,ObjectFactoryUtil.locationCode).getCode());
    }

    @Test
    @DisplayName("Get plants by location with pagination with unauthorized user")
    public void getPlantsByLocationWithPaginationWithUnauthorizedUser(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findAllByLocationCode(ObjectFactoryUtil.locationCode,ObjectFactoryUtil.pageable())).thenReturn(ObjectFactoryUtil.pagePlantList());
        assertEquals(401, plantService.getPlantsByLocationWithPagination(()->ObjectFactoryUtil.invalidEmail,5,0,ObjectFactoryUtil.locationCode).getCode());
    }

    @Test
    @DisplayName("Get plants by location with pagination with invalid location")
    public void getPlantsByLocationWithPaginationWithInvalidLocation(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findAllByLocationCode(ObjectFactoryUtil.locationCode,ObjectFactoryUtil.pageable())).thenReturn(ObjectFactoryUtil.pagePlantList());
        assertEquals(404, plantService.getPlantsByLocationWithPagination(()->ObjectFactoryUtil.validEmail,5,0,ObjectFactoryUtil.invalidLocationCode).getCode());
    }

    @Test
    @DisplayName("Get plants by location with pagination with invalid input")
    public void getPlantsByLocationWithPaginationWithInvalidInput(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findAllByLocationCode(ObjectFactoryUtil.locationCode,ObjectFactoryUtil.pageable())).thenReturn(ObjectFactoryUtil.pagePlantList());
        assertEquals(400, plantService.getPlantsByLocationWithPagination(()->ObjectFactoryUtil.validEmail,-5,0,ObjectFactoryUtil.locationCode).getCode());
    }

    @Test
    @DisplayName("Get plant details by id")
    public void getPlantDetails(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findOneById(ObjectFactoryUtil.plantId)).thenReturn(ObjectFactoryUtil.plant());
        assertEquals(200, plantService.getPlantDetails(()->ObjectFactoryUtil.validEmail,ObjectFactoryUtil.plantId).getCode());
    }

    @Test
    @DisplayName("Get plant details by id with unauthorized user")
    public void getPlantDetailsWithInvalidUser(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findOneById(ObjectFactoryUtil.plantId)).thenReturn(ObjectFactoryUtil.plant());
        assertEquals(401, plantService.getPlantDetails(()->ObjectFactoryUtil.invalidEmail,ObjectFactoryUtil.plantId).getCode());
    }

    @Test
    @DisplayName("Get plant details by id")
    public void getPlantDetailsWithInvalidInput(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findOneById(ObjectFactoryUtil.plantId)).thenReturn(ObjectFactoryUtil.plant());
        assertEquals(400, plantService.getPlantDetails(()->ObjectFactoryUtil.validEmail,-2).getCode());
    }

    @Test
    @DisplayName("Get plant details by invalid id")
    public void getPlantDetailsWithInvalidId(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findOneById(ObjectFactoryUtil.plantId)).thenReturn(ObjectFactoryUtil.plant());
        assertEquals(404, plantService.getPlantDetails(()->ObjectFactoryUtil.validEmail,7).getCode());
    }

    @Test
    @DisplayName("Get Actual and Percentage values filtered by location")
    public void getActualAndPercentageValues() throws IOException {
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findAllPlantsByLocationCode(ObjectFactoryUtil.locationCode)).thenReturn(ObjectFactoryUtil.plantListDifferent());
        when(plantRepository.findSumOfAll()).thenReturn(ObjectFactoryUtil.sum);
        assertEquals(200, plantService.getActualAndPercentageValues(()->ObjectFactoryUtil.validEmail,ObjectFactoryUtil.locationCode).getCode());
    }

    @Test
    @DisplayName("Get Actual and Percentage values filtered by location with unauthorized user")
    public void getActualAndPercentageValuesWithUnauthorizedUser() throws IOException {
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findAllPlantsByLocationCode(ObjectFactoryUtil.locationCode)).thenReturn(ObjectFactoryUtil.plantList());
        when(plantRepository.findSumOfAll()).thenReturn(ObjectFactoryUtil.sum);
        assertEquals(401, plantService.getActualAndPercentageValues(()->ObjectFactoryUtil.invalidEmail,ObjectFactoryUtil.locationCode).getCode());
    }

    @Test
    @DisplayName("Get Actual and Percentage values filtered by location with invalid input")
    public void getActualAndPercentageValuesWithInvalidInput() throws IOException {
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
        when(plantRepository.findAllPlantsByLocationCode(ObjectFactoryUtil.locationCode)).thenReturn(ObjectFactoryUtil.plantList());
        when(plantRepository.findSumOfAll()).thenReturn(ObjectFactoryUtil.sum);
        assertEquals(400, plantService.getActualAndPercentageValues(()->ObjectFactoryUtil.validEmail,null).getCode());
    }
}
