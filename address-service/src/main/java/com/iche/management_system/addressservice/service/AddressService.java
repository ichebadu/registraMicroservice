package com.iche.management_system.addressservice.service;

import com.iche.management_system.addressservice.dtos.request.AddressRequest;
import com.iche.management_system.addressservice.dtos.response.AddressResponse;
import com.iche.management_system.addressservice.entity.Address;

public interface AddressService {


    AddressResponse createAddressOfEmployee(AddressRequest request, Long employeeId);

    Address AddressRequestMapToAddress(AddressRequest request);

    AddressResponse getAddressByEmployeeId(Long addressId);

    AddressResponse getAddress(Long addressId);
}
