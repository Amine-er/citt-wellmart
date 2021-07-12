package com.citt.wellmart.controller.mappers;

import com.citt.wellmart.controller.models.MerchantDto;
import com.citt.wellmart.controller.models.PersonDto;
import com.citt.wellmart.entities.Company;
import com.citt.wellmart.entities.Merchant;
import com.citt.wellmart.entities.Person;
import org.mapstruct.Mapper;

@Mapper
public interface MerchantMapper {
    Person toMerchant(MerchantDto merchantDto);
    MerchantDto toMerchantDto(Merchant merchant);
    Company toCompany(MerchantDto merchantDto);
    PersonDto toPersonDto(Merchant merchant);
    MerchantDto toCompanyDto(Company company);
}
