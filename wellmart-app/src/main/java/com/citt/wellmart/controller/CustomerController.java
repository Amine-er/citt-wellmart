package com.citt.wellmart.controller;

import com.citt.wellmart.entities.Customer;
import com.citt.wellmart.entities.security.User;
import com.citt.wellmart.repositories.RoleRepository;
import com.citt.wellmart.repositories.UserRepository;
import com.citt.wellmart.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
 private RoleRepository roleRepository ;
    @Autowired
    private UserRepository userRepository ;
    @PostMapping
    public void saveCustomer(@RequestBody Customer customer) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Optional<User>  userExist = userRepository.findByUsername(customer.getEmail());
        if(userExist.isPresent()){
            throw new Exception("Customer already exist !");
        }

        User user1 = User.builder()
                .username(customer.getEmail())
                .password(passwordEncoder.encode(customer.getPassword()))
                .firstName(customer.getName())
                .lastName(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .role(roleRepository.findByName("MERCHANT"))
                .build();

        userRepository.save(user1);
    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer){ return customerService.updateCustomer(customer);}
    @GetMapping
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }
    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable(name = "id") Long id){ customerService.deleteCustomerById(id); }

}
