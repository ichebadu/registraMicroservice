package com.iche.management_system.addressservice.repository;

import com.iche.management_system.addressservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {
}
