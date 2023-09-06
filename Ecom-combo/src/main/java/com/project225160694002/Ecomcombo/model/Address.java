package com.project225160694002.Ecomcombo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address_line_1", nullable = false,length = 500)
    private String address_line1;

    @Column(name = "address_line_2", length = 500)
    private String address_line2;

    @Column(name = "city", nullable = false, length = 250)
    private String city;

    @Column(name = "country", length = 75)
    private String country;

    @Column(name = "pincode", nullable = false)
    private Long pincode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "local_user_id", nullable = false)
    private LocalUser localUser;

}