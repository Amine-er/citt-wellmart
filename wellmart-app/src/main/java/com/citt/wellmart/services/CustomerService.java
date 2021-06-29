package com.citt.wellmart.services;

import com.citt.wellmart.entities.Customer;
import com.citt.wellmart.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer){ return customerRepository.save(customer);}

    public Customer updateCustomer(Customer customer){ return customerRepository.save(customer);}

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public void deleteCustomerById(Long id){ customerRepository.deleteById(id); }

}
