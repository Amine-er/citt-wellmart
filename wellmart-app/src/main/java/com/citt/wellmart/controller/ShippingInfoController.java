package com.citt.wellmart.controller;

import com.citt.wellmart.entities.ShippingInfo;
import com.citt.wellmart.services.ShippingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shippingInfo")
public class ShippingInfoController {
    @Autowired
    private ShippingInfoService shippingInfoService;

    @PostMapping
    public ShippingInfo saveShippingInfo(@RequestBody ShippingInfo shippingInfo)
    { return shippingInfoService.saveShippingInfo(shippingInfo); }
    @PutMapping
    public ShippingInfo updateShippingInfo(@RequestBody ShippingInfo shippingInfo)
    {return shippingInfoService.updateShippingInfo(shippingInfo); }
    @GetMapping
    public List<ShippingInfo> getAllShippingInfo()
    {return shippingInfoService.getAllShippingInfo();}
    @DeleteMapping(value = "/{id}")
    public void deleteShippingInfoById(@PathVariable(name = "id") Long id)
    {shippingInfoService.deleteShippingInfoById(id);}
}
