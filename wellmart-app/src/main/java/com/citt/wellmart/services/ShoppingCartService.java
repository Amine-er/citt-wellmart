package com.citt.wellmart.services;

import com.citt.wellmart.entities.ShoppingCart;
import com.citt.wellmart.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart)
    { return shoppingCartRepository.save(shoppingCart); }

    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart)
    {return shoppingCartRepository.save(shoppingCart); }

    public List<ShoppingCart> getAllShoppingCart()
    {return shoppingCartRepository.findAll();}

    public void deleteShoppingCartById(Long id)
    {shoppingCartRepository.deleteById(id);}
}
