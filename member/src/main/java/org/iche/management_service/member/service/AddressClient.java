package org.iche.management_service.member.service;

import org.iche.management_service.member.dto.request.EmployeeRegistration;
import org.iche.management_service.member.dto.response.ApiResponse;
import org.iche.management_service.member.dto.response.EmployeeId;
import org.iche.management_service.member.dto.response.EmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ADDRESS-SERVICE", path = "/address-app/api")
public interface AddressClient {
    @PostMapping("/employee/detail")
    public ResponseEntity<ApiResponse<EmployeeResponse>> employeeRegistration(@RequestBody EmployeeRegistration employeeRegistration);
    @GetMapping("/employee/{id}")
    public ResponseEntity<ApiResponse<EmployeeId>> getEmployeeById(@PathVariable("id") Long id);
}
