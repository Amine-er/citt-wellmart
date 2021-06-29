package com.citt.wellmart.controller;

import com.citt.wellmart.entities.Customer;
import com.citt.wellmart.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer){ return customerService.saveCustomer(customer);}
    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer){ return customerService.updateCustomer(customer);}
    @GetMapping
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }
    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable(name = "id") Long id){ customerService.deleteCustomerById(id); }

}
