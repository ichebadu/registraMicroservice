package org.iche.management_service.member.service;


import org.iche.management_service.member.dto.request.EmployeeRegistration;
import org.iche.management_service.member.dto.response.AddressResponse;
import org.iche.management_service.member.dto.response.EmployeeId;
import org.iche.management_service.member.dto.response.EmployeeResponse;
import org.iche.management_service.member.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    EmployeeResponse employeeRegistration(EmployeeRegistration employeeRegistration);

    Employee employeeRegistrationToEmployee(EmployeeRegistration employeeRegistration);
    EmployeeId getEmployeeById(Long id);
    EmployeeResponse getEmployeeDetails(Long id);

    // for restTemplate calls
    AddressResponse callToAddressServiceUsingWebClient(Long id);
}
