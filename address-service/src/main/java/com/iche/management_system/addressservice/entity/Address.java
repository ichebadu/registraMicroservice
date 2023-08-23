package com.iche.management_system.addressservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="Lane1")
    private String Lane1;
    @Column(name="Lane2")
    private String Lane2;
    @Column(name="zip")
    private long zip;
    @Column(name="state")
    private String state;
    @Column(name="country")
    private String country ;
}
