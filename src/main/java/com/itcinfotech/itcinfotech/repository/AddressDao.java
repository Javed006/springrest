package com.itcinfotech.itcinfotech.repository;

import com.itcinfotech.itcinfotech.entities.AddressDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<AddressDto,Integer> {
}
