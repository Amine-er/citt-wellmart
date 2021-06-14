package com.citt.wellmart.controller;

import com.citt.wellmart.entities.DeliveryMan;
import com.citt.wellmart.services.DeliveryManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryMen")
public class DeliveryManController {
    @Autowired
    private DeliveryManService deliveryManService;

    @PostMapping
    public DeliveryMan saveDeliveryMan(@RequestBody DeliveryMan deliveryMan)
    { return deliveryManService.saveDeliveryMan(deliveryMan);}
    @PutMapping
    public DeliveryMan updateDeliveryMan(@RequestBody DeliveryMan deliveryMan)
    { return deliveryManService.updateDeliveryMan(deliveryMan);}
    @GetMapping
    public List<DeliveryMan> getAllDeliveryMan(){
        return deliveryManService.getAllDeliveryMan();
    }
    @DeleteMapping(value = "/{id}")
    public void deleteDeliveryManById(@PathVariable(name = "id") Long id){ deliveryManService.deleteDeliveryManById(id); }
}
