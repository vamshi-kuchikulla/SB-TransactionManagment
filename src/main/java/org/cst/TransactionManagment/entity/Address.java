package org.cst.TransactionManagment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address_tbl")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private String doorNumber;
    private String street;
    private String city;
    private String state;
    private int pinCode;
    public Address(String doorNumber, String street, String city, String state, int pinCode) {
    }
}
