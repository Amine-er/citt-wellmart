package com.citt.wellmart.bootstrap;


import com.citt.wellmart.entities.security.Authority;
import com.citt.wellmart.entities.security.Role;
import com.citt.wellmart.entities.security.User;
import com.citt.wellmart.repositories.AuthorityRepository;
import com.citt.wellmart.repositories.RoleRepository;
import com.citt.wellmart.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDataLoader implements CommandLineRunner {

    private final UserRepository userRepository ;
    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository ;

    @Override
    public void run(String... args) throws Exception {
        this.loadUser();
    }

    @Transactional
    private void loadUser() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Authority createMerchant = authorityRepository.save(Authority.builder().permission("create_merchant").build());
        Authority createCategory = authorityRepository.save(Authority.builder().permission("create_category").build());
        Authority createProduct = authorityRepository.save(Authority.builder().permission("create_product").build());
        Authority createReference   = authorityRepository.save(Authority.builder().permission("create_references").build());
        Authority getMerchant   = authorityRepository.save(Authority.builder().permission("get_merchant").build());
        Authority getReference   = authorityRepository.save(Authority.builder().permission("get_references").build());
        Authority getProduct   = authorityRepository.save(Authority.builder().permission("get_product").build());
        Authority getCategory   = authorityRepository.save(Authority.builder().permission("get_category").build());
        Authority getOrders   = authorityRepository.save(Authority.builder().permission("get_orders").build());


        Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
        Role merchantRole = roleRepository.save(Role.builder().name("MERCHANT").build());
        Role guestRole = roleRepository.save(Role.builder().name("GUEST").build());

        adminRole.setAuthorities(Set.of(createMerchant,createCategory,createProduct,createReference,getMerchant,getReference,getProduct,getCategory,getOrders));
        merchantRole.setAuthorities(Set.of(createCategory,createProduct,getMerchant,getReference,getProduct,getCategory,getOrders));


        roleRepository.saveAll(Arrays.asList(adminRole,merchantRole,guestRole));
        log.info("Creating users ....");
        User user1 = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .firstName("admin")
                .lastName("admin")
                .email("admin@gmail.com")
                .phone("0607707989")
                .role(adminRole) // updated using the CascadType.Merge
                .build();

        User user2 = User.builder()
                .username("merchant")
                .firstName("merchant")
                .lastName("merchnat")
                .email("merchant@gmail.com")
                .phone("0607707989")
                .password(passwordEncoder.encode("admin"))
                .role(merchantRole) // updated using the CascadType.Merge
                .build();
        userRepository.save(user2);


        userRepository.save(user1);
        userRepository.save(user2);
    }

}
