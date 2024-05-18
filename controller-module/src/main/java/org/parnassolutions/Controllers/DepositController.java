package org.parnassolutions.Controllers;

import lombok.RequiredArgsConstructor;
import org.parnassolutions.DTOs.DepositDTO;
import org.parnassolutions.Entities.Deposit;
import org.parnassolutions.Services.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/deposits")
@RequiredArgsConstructor
public class DepositController {
    private final DepositService depositService;

    @GetMapping(value = "/{depositId}", produces = APPLICATION_JSON_VALUE)
    public Deposit findByDepositId(@PathVariable("depositId") Long depositId) {
        return depositService.findByDepositId(depositId);
    }

    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public List<Deposit> findAllDeposits() {
        return depositService.findAllDeposits();
    }

    @GetMapping(value = "/count")
    public Long getDepositsCount() {
        return depositService.getDepositsCount();
    }

    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Deposit addDeposit(@RequestBody DepositDTO dto) {
        return depositService.addDeposit(dto);
    }

    @PostMapping(value = "/update/{depositId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Deposit updateDeposit(@PathVariable("depositId") Long depositId, @RequestBody DepositDTO dto) {
        return depositService.updateDepositById(depositId, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/delete/{depositId}", produces = APPLICATION_JSON_VALUE)
    public void deleteDeposit(@PathVariable("depositId") Long depositId) {
        depositService.deleteDepositById(depositId);
    }
}
