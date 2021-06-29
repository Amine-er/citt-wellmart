package com.citt.wellmart.services;

import com.citt.wellmart.entities.DeliveryMan;
import com.citt.wellmart.entities.Product;
import com.citt.wellmart.repositories.DeliveryManRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryManService {
    @Autowired
    private DeliveryManRepository deliveryManRepository;

    public DeliveryMan saveDeliveryMan(DeliveryMan deliveryMan)
    { return deliveryManRepository.save(deliveryMan);}

    public DeliveryMan updateDeliveryMan(DeliveryMan deliveryMan)
    { return deliveryManRepository.save(deliveryMan);}

    public List<DeliveryMan> getAllDeliveryMan(){
        return deliveryManRepository.findAll();
    }

    public void deleteDeliveryManById(Long id){ deliveryManRepository.deleteById(id); }

}
