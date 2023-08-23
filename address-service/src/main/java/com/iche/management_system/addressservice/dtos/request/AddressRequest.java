package com.iche.management_system.addressservice.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    private String Lane1;

    private String Lane2;

    private long zip;

    private String state;

    private String country ;
}
