package org.cst.TransactionManagment.dto;

import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressDto {
    private String doorNumber;
    private String street;
    private String city;
    private String state;
    private int pinCode;
}
