package org.parnassolutions.DTOs;

import lombok.Data;
import org.parnassolutions.Enums.Currency;

import java.util.List;

@Data
public class AccountDTO {
    private Long userId;
    private String title;
    private Long balance;
    private Currency currency;
    private List<Long> operations;
}
