package com.iche.management_system.addressservice.service;

import com.iche.management_system.addressservice.dtos.request.AddressRequest;
import com.iche.management_system.addressservice.dtos.response.AddressResponse;
import com.iche.management_system.addressservice.entity.Address;
import com.iche.management_system.addressservice.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    @Override
    public AddressResponse createAddressOfEmployee(AddressRequest request, Long employeeId) {

        Address address = AddressRequestMapToAddress(request);
        Address savedAddress = addressRepository.save(address); // Save the address and get the saved instance

        // Use the saved address to ensure that the generated id is available
        restTemplate.getForObject("http://localhost:8047/employee-app/api/employee/{employeeId}", AddressResponse.class, employeeId);
        System.out.println(savedAddress.getId()); // Now you can access the generated id

        return AddressResponse.builder()
                .Lane1(request.getLane1())
                .Lane2(request.getLane2())
                .zip(request.getZip())
                .state(request.getState())
                .country(request.getCountry())
                .build();
    }

    @Override
    public Address AddressRequestMapToAddress(AddressRequest request) {
        return modelMapper.map(request, Address.class);
    }

    @Override
    public AddressResponse getAddressByEmployeeId(Long employeeId) {

        Address address = addressRepository.findById(employeeId).get();
        restTemplate.getForObject("http://localhost:8047/employee-app/api/employee/{employeeId}", AddressResponse.class, employeeId);

        return AddressResponse.builder()
                .Lane1(address.getLane1())
                .Lane2(address.getLane2())
                .zip(address.getZip())
                .state(address.getState())
                .country(address.getCountry())
                .build();
    }

    @Override
    public AddressResponse getAddress(Long addressId) {

        Address address = addressRepository.findById(addressId).orElseThrow(()-> new RuntimeException("Address not found"));


        return AddressResponse.builder()
                .Lane1(address.getLane1())
                .Lane2(address.getLane2())
                .zip(address.getZip())
                .state(address.getState())
                .country(address.getCountry())
                .build();
    }
}
