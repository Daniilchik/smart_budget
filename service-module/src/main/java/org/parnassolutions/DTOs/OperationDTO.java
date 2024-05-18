package org.parnassolutions.DTOs;

import lombok.Data;
import org.parnassolutions.Enums.OperationType;

import java.util.Date;

@Data
public class OperationDTO {
    private Long accountId;
    private Double amount;
    private String title;
    private String description;
    private Date date;
    private OperationType operationType;
}
