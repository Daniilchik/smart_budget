package org.parnassolutions.DTOs;

import lombok.Data;

@Data
public class AccountDTO {
    private Integer userId;
    private Long budget;
    private Long income;
    private Long expense;
}
