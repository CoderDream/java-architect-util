package com.coderdream.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankInfoDto {

    private String name;

    private String bankName;
    private String cardNo;
    private BigDecimal balance;
    private String accountNo;
}
