package com.example.powerplant.objectFactoryUtils;

import com.example.powerplant.plant.model.Plant;
import com.example.powerplant.user.model.User;
import org.springframework.data.domain.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactoryUtil {

    public static String validEmail = "faarid@gmail.com";
    public static String invalidEmail = "faarid124@gmail.com";
    public static String locationCode = "AK";
    public static String invalidLocationCode = "AKA";
    public static long plantId = 1l;
    public static double sum = 5435431.324;

    public static User userValid() {
        User user = new User();
        user.setId(1l);
        user.setEmail(validEmail);
        user.setPassword("Hello123#");
        return user;
    }

    public static Plant plant() {
        Plant plant = new Plant();
        plant.setId(1l);
        plant.setPlantName("Ambler");
        plant.setYear("2019");
        plant.setGenAnnualNet(19.244);
        plant.setGenStatus("RE");
        plant.setLocationCode("AK");
        plant.setGenId("644F");
        return plant;
    }

    public static Plant differentPlant() {
        Plant plant = new Plant();
        plant.setId(2l);
        plant.setPlantName("Yakrut");
        plant.setYear("2014");
        plant.setGenAnnualNet(29.244);
        plant.setGenStatus("RE");
        plant.setLocationCode("AK");
        plant.setGenId("644A");
        return plant;
    }

    public static Pageable pageable(){
        return PageRequest.of(0, 5, Sort.Direction.ASC, "id");
    }
    public static Pageable bottomPageable(){
        return PageRequest.of(0, 5, Sort.Direction.ASC, "genAnnualNet");
    }
    public static Pageable topPageable(){
        return PageRequest.of(0, 5, Sort.Direction.DESC, "genAnnualNet");
    }

    public static Page<Plant> pagePlantList(){
        List<Plant> plantList = new ArrayList<>();
        plantList.add(plant());
        plantList.add(plant());
        plantList.add(plant());
        plantList.add(plant());
        plantList.add(plant());
        Page<Plant> page = new PageImpl<>(plantList);
        return page;
    }

    public static List<Plant> plantList(){
        List<Plant> plantList = new ArrayList<>();
        plantList.add(plant());
        plantList.add(plant());
        plantList.add(plant());
        plantList.add(plant());
        plantList.add(plant());
        return plantList;
    }

    public static List<Plant> plantListDifferent(){
        List<Plant> plantList = new ArrayList<>();
        plantList.add(plant());
        plantList.add(plant());
        plantList.add(plant());
        plantList.add(plant());
        plantList.add(differentPlant());
        return plantList;
    }
}
