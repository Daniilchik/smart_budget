package org.parnassolutions.Controllers;

import lombok.RequiredArgsConstructor;
import org.parnassolutions.DTOs.SavingDTO;
import org.parnassolutions.Entities.Saving;
import org.parnassolutions.Services.SavingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/savings")
@RequiredArgsConstructor
public class SavingController {
    private final SavingService savingService;

    @GetMapping(value = "/{savingId}", produces = APPLICATION_JSON_VALUE)
    public Saving findBySavingId(@PathVariable("savingId") Long savingId) {
        return savingService.findBySavingId(savingId);
    }

    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public List<Saving> findAllSavings() {
        return savingService.findAllSavings();
    }

    @GetMapping(value = "/count")
    public Long getSavingsCount() {
        return savingService.getSavingsCount();
    }
//
//    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//    public Saving addSaving(@RequestBody SavingDTO dto) {
//        return savingService.addSaving(dto);
//    }

//    @PostMapping(value = "/update/{savingId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//    public Saving updateSaving(@PathVariable("savingId") Long savingId, @RequestBody SavingDTO dto) {
//        return savingService.updateSavingById(savingId, dto);
//    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/delete/{savingId}", produces = APPLICATION_JSON_VALUE)
    public void deleteSaving(@PathVariable("savingId") Long savingId) {
        savingService.deleteSavingById(savingId);
    }
}
