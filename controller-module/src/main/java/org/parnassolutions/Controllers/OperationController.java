package org.parnassolutions.Controllers;

import lombok.RequiredArgsConstructor;
import org.parnassolutions.Entities.Expense;
import org.parnassolutions.Entities.Operation;
import org.parnassolutions.Services.OperationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationController {
    private final OperationService operationService;

    @GetMapping(value = "/{operationId}", produces = APPLICATION_JSON_VALUE)
    public Operation findByOperationId(@PathVariable("operationId") Long operationId) {
        return operationService.findByOperationId(operationId);
    }

    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public List<Operation> findAllOperations() {
        return operationService.findAllOperations();
    }

    @GetMapping(value = "/count")
    public Long getOperationsCount() {
        return operationService.getOperationsCount();
    }
}
