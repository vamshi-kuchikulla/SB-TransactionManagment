package org.cst.TransactionManagment.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDto {
    private int rollNo;
    private String fullName;
    @Email
    private String email;
    private long mobileNumber;
    private List<AddressDto> address;
}
