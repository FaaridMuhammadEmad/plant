package com.example.powerplant.plant.service;

import com.example.powerplant.util.Message;

import java.io.IOException;
import java.security.Principal;

public interface IPlantService {
     Message getTopAndBottomPlants(Principal principal, int n);
     Message getFilterPlantsByLocation(Principal principal, String locationCode);
     Message getPlantsByLocationWithPagination(Principal principal, int pageSize,int pageNumber, String location);
    Message getPlantDetails(Principal principal, long id);
    Message getActualAndPercentageValues(Principal principal,String locationCode) throws IOException;
    }
