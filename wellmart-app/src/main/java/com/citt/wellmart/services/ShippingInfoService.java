package com.citt.wellmart.services;

import com.citt.wellmart.entities.ShippingInfo;
import com.citt.wellmart.repositories.ShippingInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingInfoService {
    @Autowired
    private ShippingInfoRepository shippingInfoRepository;

    public ShippingInfo saveShippingInfo(ShippingInfo shippingInfo)
    { return shippingInfoRepository.save(shippingInfo); }

    public ShippingInfo updateShippingInfo(ShippingInfo shippingInfo)
    {return shippingInfoRepository.save(shippingInfo); }

    public List<ShippingInfo> getAllShippingInfo()
    {return shippingInfoRepository.findAll();}

    public void deleteShippingInfoById(Long id)
    {shippingInfoRepository.deleteById(id);}
}
