package com.iche.management_system.addressservice.controller;

import com.iche.management_system.addressservice.dtos.request.AddressRequest;
import com.iche.management_system.addressservice.dtos.response.AddressResponse;
import com.iche.management_system.addressservice.dtos.response.ApiResponse;
import com.iche.management_system.addressservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/create-address/{employeeId}")
    public ResponseEntity<ApiResponse<AddressResponse>> createAddressOfEmployee(@RequestBody AddressRequest request, @PathVariable("employeeId") Long id) {
        ApiResponse<AddressResponse> apiResponse = new ApiResponse<>(addressService.createAddressOfEmployee(request, id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/address/employee/{employeeId}")
    public ResponseEntity<ApiResponse<AddressResponse>> getAddressByEmployee(@PathVariable("employeeId") Long id) {
        ApiResponse<AddressResponse> apiResponse = new ApiResponse<>(addressService.getAddressByEmployeeId(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<ApiResponse<AddressResponse>> getAddress(@PathVariable("id") Long id) {
        ApiResponse<AddressResponse> apiResponse = new ApiResponse<>(addressService.getAddress(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
