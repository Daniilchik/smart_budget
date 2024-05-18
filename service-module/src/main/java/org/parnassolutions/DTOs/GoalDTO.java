package org.parnassolutions.DTOs;

import lombok.Data;

import java.util.Date;

@Data
public class GoalDTO {
    private Long userId;
    private Double total;
    private Double balance;
    private Date reachDate;
}
