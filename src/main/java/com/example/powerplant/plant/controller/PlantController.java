package com.example.powerplant.plant.controller;

import com.example.powerplant.plant.service.IPlantService;
import com.example.powerplant.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/1.0/plant")
public class PlantController {

    @Autowired
    IPlantService plantService;

    @GetMapping("/{n}")
    public ResponseEntity getTopAndBottomPlants(Principal principal,@PathVariable(name="n") int n){
        Message message = plantService.getTopAndBottomPlants(principal,n);
        return ResponseEntity.status(message.getCode()).body(message);
    }

    @GetMapping()
    public ResponseEntity getFilterPlantsByLocation(Principal principal,@RequestParam String location){
        Message message = plantService.getFilterPlantsByLocation(principal,location);
        return ResponseEntity.status(message.getCode()).body(message);
    }

    @GetMapping("/pagination")
    public ResponseEntity getPlantsByLocationWithPagination(Principal principal,@RequestParam String location,@RequestParam int pageSize,@RequestParam int pageNumber){
        Message message = plantService.getPlantsByLocationWithPagination(principal,pageSize,pageNumber,location);
        return ResponseEntity.status(message.getCode()).body(message);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity getPlantDetails(Principal principal,@PathVariable(name="id") long id){
        Message message = plantService.getPlantDetails(principal,id);
        return ResponseEntity.status(message.getCode()).body(message);
    }

    @GetMapping("/percentage")
    public ResponseEntity getActualAndPercentageValues(Principal principal,@RequestParam String location) throws IOException {
        Message message = plantService.getActualAndPercentageValues(principal,location);
        return ResponseEntity.status(message.getCode()).body(message);
    }
}
