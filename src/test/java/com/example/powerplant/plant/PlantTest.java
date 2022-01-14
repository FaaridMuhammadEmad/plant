package com.example.powerplant.plant;

import com.example.powerplant.objectFactoryUtils.ObjectFactoryUtil;
import com.example.powerplant.plant.model.Plant;
import com.example.powerplant.plant.repository.PlantRepository;
import com.example.powerplant.plant.service.PlantService;
import com.example.powerplant.user.model.User;
import com.example.powerplant.user.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlantTest {

    @Autowired
    PlantService plantService;

    @MockBean
    PlantRepository plantRepository;

    @MockBean
    UserRepository userRepository;

    @Test
    @DisplayName("Getting top and bottom plants")
    public void getTopAndBottomPlants(){
        when(userRepository.findOneByEmail(ObjectFactoryUtil.validEmail)).thenReturn(ObjectFactoryUtil.userValid());
       when(plantRepository.findAllByGenAnnualNetIsNotNull(ObjectFactoryUtil.topPageable())).thenReturn(ObjectFactoryUtil.plantList());
       when(plantRepository.findAllByGenAnnualNetIsNotNull(ObjectFactoryUtil.bottomPageable())).thenReturn(ObjectFactoryUtil.plantList());
        assertEquals(200, plantService.getTopAndBottomPlants(()->ObjectFactoryUtil.validEmail,5).getCode());

    }
}
