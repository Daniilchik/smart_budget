package org.parnassolutions.DTOs;

import lombok.Data;

@Data
public class ExpenseDTO extends OperationDTO {
    private String category;
}
