package com.citt.wellmart;

import com.citt.wellmart.repositories.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WellmartAppApplication {

	@Autowired
	private MerchantRepository merchantRepository ;

	public static void main(String[] args) {
		SpringApplication.run(WellmartAppApplication.class, args);
	}

}
