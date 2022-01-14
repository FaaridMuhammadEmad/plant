package com.example.powerplant.plant.service;

import com.example.powerplant.global.ConstantValues;
import com.example.powerplant.plant.dto.TopBottomListDto;
import com.example.powerplant.plant.model.Plant;
import com.example.powerplant.plant.repository.PlantRepository;
import com.example.powerplant.user.model.User;
import com.example.powerplant.user.repository.UserRepository;
import com.example.powerplant.util.Message;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.security.Principal;

@Service
public class PlantService implements IPlantService {
    @Autowired
    PlantRepository plantRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Message getTopAndBottomPlants(Principal principal, int n) {
        Message message = new Message();
        try {
            if (principal != null && n > 0) {
                User user = userRepository.findOneByEmail(principal.getName());
                if (user != null) {
                    Pageable bottomPageable = PageRequest.of(0, n, Sort.Direction.ASC, "genAnnualNet");
                    Pageable topPageable = PageRequest.of(0, n, Sort.Direction.DESC, "genAnnualNet");
                    Page<Plant> topList = plantRepository.findAllByGenAnnualNetIsNotNull(topPageable);
                    Page<Plant> bottomList = plantRepository.findAllByGenAnnualNetIsNotNull(bottomPageable);
                    TopBottomListDto topBottomListDto = new TopBottomListDto(topList.getContent(), bottomList.getContent());
                    message.setMessage("List found").setData(topBottomListDto).setCode(ConstantValues.SERVICE_OK).setStatus(ConstantValues.SERVICE_SUCCESS_STATUS);
                } else {
                    message.setCode(ConstantValues.SERVICE_UNAUTHORIZED).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("User unauthorized");
                }
            } else {
                message.setCode(ConstantValues.SERVICE_BAD_REQUEST).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Please enter valid inputs");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message.setCode(ConstantValues.SERVICE_INTERNAL_SERVER).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Something went wrong");
        }
        return message;
    }

    @Override
    public Message getFilterPlantsByLocation(Principal principal, String locationCode) {
        Message message = new Message();
        try {
            if (principal != null && locationCode != null) {
                User user = userRepository.findOneByEmail(principal.getName());
                if (user != null) {
                    List<Plant> plantList = plantRepository.findAllPlantsByLocationCode(locationCode);
                    if (!plantList.isEmpty()) {
                        message.setData(plantList).setMessage("List found").setCode(ConstantValues.SERVICE_OK).setStatus(ConstantValues.SERVICE_SUCCESS_STATUS);
                    } else {
                        message.setCode(ConstantValues.SERVICE_NOT_FOUND).setStatus(ConstantValues.SERVICE_SUCCESS_STATUS).setMessage("Plants not found");
                    }
                } else {
                    message.setCode(ConstantValues.SERVICE_UNAUTHORIZED).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("User unauthorized");
                }
            } else {
                message.setCode(ConstantValues.SERVICE_BAD_REQUEST).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Please enter valid inputs");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message.setCode(ConstantValues.SERVICE_INTERNAL_SERVER).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Something went wrong");
        }
        return message;


    }

    @Override
    public Message getPlantsByLocationWithPagination(Principal principal, int pageSize, int pageNumber, String location) {
        Message message = new Message();
        try {
            if (principal != null && location != null && pageSize > 0 && pageNumber >= 0) {
                User user = userRepository.findOneByEmail(principal.getName());
                if (user != null) {
                    Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "id");
                    Page<Plant> plantList = plantRepository.findAllByLocationCode(location, pageable);
                    if (plantList != null && !plantList.isEmpty()) {
                        message.setData(plantList).setMessage("List found").setCode(ConstantValues.SERVICE_OK).setStatus(ConstantValues.SERVICE_SUCCESS_STATUS);
                    } else {
                        message.setCode(ConstantValues.SERVICE_NOT_FOUND).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Plants not found");
                    }
                } else {
                    message.setCode(ConstantValues.SERVICE_UNAUTHORIZED).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("User unauthorized");
                }
            } else {
                message.setCode(ConstantValues.SERVICE_BAD_REQUEST).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Please enter valid inputs");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message.setCode(ConstantValues.SERVICE_INTERNAL_SERVER).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Something went wrong");
        }
        return message;
    }

    @Override
    public Message getPlantDetails(Principal principal, long id) {
        Message message = new Message();
        try {
            if (principal != null && id > 0) {
                User user = userRepository.findOneByEmail(principal.getName());
                if (user != null) {
                    Plant plant = plantRepository.findOneById(id);
                    if (plant != null) {
                        message.setData(plant).setMessage("Plant found").setCode(ConstantValues.SERVICE_OK).setStatus(ConstantValues.SERVICE_SUCCESS_STATUS);
                    } else {
                        message.setCode(ConstantValues.SERVICE_NOT_FOUND).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Plant not found");
                    }
                } else {
                    message.setCode(ConstantValues.SERVICE_UNAUTHORIZED).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("User unauthorized");
                }
            } else {
                message.setCode(ConstantValues.SERVICE_BAD_REQUEST).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Please enter valid inputs");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message.setCode(ConstantValues.SERVICE_INTERNAL_SERVER).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Something went wrong");
        }
        return message;
    }

    @Override
    public Message getActualAndPercentageValues(Principal principal, String locationCode) throws IOException {
        Message message = new Message();
        try{
            if (principal != null && locationCode!=null) {
                User user = userRepository.findOneByEmail(principal.getName());
                if (user != null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Double sumOfAll = plantRepository.findSumOfAll();
                    ArrayList<Plant> plantList = (ArrayList<Plant>) plantRepository.findAllPlantsByLocationCode(locationCode);
                    List<Plant> list = new ArrayList<>();
                    Double sum = 0.0;
                    JSONArray jsonArray = new JSONArray();
                    JSONObject deepCopy;
                    for (int i = 0; i < plantList.size() - 1; i++) {
                        if (plantList.get(i).getPlantName().equals(plantList.get(i + 1).getPlantName())) {
                            if (plantList.get(i).getGenAnnualNet() != null) {
                                sum = sum + plantList.get(i).getGenAnnualNet();
                            }
                            list.add(plantList.get(i));
                        } else {
                            if (plantList.get(i).getGenAnnualNet() != null) {
                                sum = sum + plantList.get(i).getGenAnnualNet();
                            }
                            list.add(plantList.get(i));
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put(plantList.get(i).getPlantName(), list);
                            jsonObject.put("Percentage", (sum / sumOfAll) * 100 + " %");
                            deepCopy = objectMapper.readValue(objectMapper.writeValueAsString(jsonObject), JSONObject.class);
                            jsonArray.add(deepCopy);
                            list.clear();
                            sum = 0.0;
                        }
                    }
                    message.setCode(ConstantValues.SERVICE_OK).setStatus(ConstantValues.SERVICE_SUCCESS_STATUS).setMessage("List found").setData(jsonArray);
                } else {
                    message.setCode(ConstantValues.SERVICE_UNAUTHORIZED).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("User unauthorized");
                }
            }
                else{
                message.setCode(ConstantValues.SERVICE_BAD_REQUEST).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Please enter valid inputs");
            }
        }catch (Exception e){
            e.printStackTrace();
            message.setCode(ConstantValues.SERVICE_INTERNAL_SERVER).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Something went wrong");
        }
        return message;
    }
}



