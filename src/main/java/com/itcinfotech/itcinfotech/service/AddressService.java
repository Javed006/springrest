package com.itcinfotech.itcinfotech.service;

import com.itcinfotech.itcinfotech.entities.AddressDto;
import com.itcinfotech.itcinfotech.pojo.Address;
import com.itcinfotech.itcinfotech.repository.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressDao addressRepository;
    public AddressDto addAddress(AddressDto address) {
        AddressDto addressSavedToDB = this.addressRepository.save(address);
        return addressSavedToDB;
    }
}
