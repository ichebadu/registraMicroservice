package org.iche.management_service.member.controller;

import lombok.RequiredArgsConstructor;
import org.iche.management_service.member.dto.request.EmployeeRegistration;
import org.iche.management_service.member.dto.response.ApiResponse;
import org.iche.management_service.member.dto.response.EmployeeId;
import org.iche.management_service.member.dto.response.EmployeeResponse;
import org.iche.management_service.member.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;


    @PostMapping("/employee/detail")
    public ResponseEntity<ApiResponse<EmployeeResponse>> employeeRegistration(@RequestBody EmployeeRegistration employeeRegistration){
        ApiResponse<EmployeeResponse> employeeResponse = new ApiResponse<>(employeeService.employeeRegistration(employeeRegistration));
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<ApiResponse<EmployeeId>> getEmployeeById(@PathVariable("id") Long id){
        ApiResponse<EmployeeId> apiResponse = new ApiResponse<>(employeeService.getEmployeeById(id));
        return new ResponseEntity(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/employee-details/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployeeDetails(@PathVariable("id") Long id){
        ApiResponse<EmployeeResponse> employeeResponse = new ApiResponse<>(employeeService.getEmployeeDetails(id));
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }
}
