package com.iche.management_system.addressservice.dtos.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.iche.management_system.addressservice.Utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {"message", "time", "data"})
public class ApiResponse <T>{
    private String message;
    private String time;
    private T data;

    public ApiResponse(T data) {
        this.message = "processed Successfully";
        this.time = DateUtils.saveDate(LocalDateTime.now());
        this.data = data;
    }
}