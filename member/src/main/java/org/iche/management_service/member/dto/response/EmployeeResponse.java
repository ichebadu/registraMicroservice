package org.iche.management_service.member.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    private String name;

    private String email;

    private String bloodGroup;
    private AddressResponse addressResponse;

}