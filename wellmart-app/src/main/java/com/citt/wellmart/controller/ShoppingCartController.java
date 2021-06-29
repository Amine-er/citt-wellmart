package com.citt.wellmart.controller;

import com.citt.wellmart.entities.ShoppingCart;
import com.citt.wellmart.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCarts")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @PostMapping
    public ShoppingCart saveShoppingCart(@RequestBody ShoppingCart shoppingCart)
    { return shoppingCartService.saveShoppingCart(shoppingCart); }
    @PutMapping
    public ShoppingCart updateShoppingCart(@RequestBody ShoppingCart shoppingCart)
    {return shoppingCartService.updateShoppingCart(shoppingCart); }
    @GetMapping
    public List<ShoppingCart> getAllShoppingCart()
    {return shoppingCartService.getAllShoppingCart();}
    @DeleteMapping(value = "/{id}")
    public void deleteShoppingCartById(@PathVariable(name = "id") Long id)
    {shoppingCartService.deleteShoppingCartById(id);}
}
