package com.citt.wellmart.repositories;

import com.citt.wellmart.entities.ProductExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductExperienceRepository extends JpaRepository<ProductExperience, Long> {
}
