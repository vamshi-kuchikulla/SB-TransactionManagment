package org.cst.TransactionManagment.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {
    private LocalDateTime timeStamp;
    private String  message;
    private String details;
}
