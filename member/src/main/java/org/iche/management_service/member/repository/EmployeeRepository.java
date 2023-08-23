package org.iche.management_service.member.repository;


import org.iche.management_service.member.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

