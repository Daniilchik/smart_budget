package org.parnassolutions.Controllers;

import lombok.RequiredArgsConstructor;
import org.parnassolutions.DTOs.GoalDTO;
import org.parnassolutions.Entities.Goal;
import org.parnassolutions.Services.GoalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/goals")
@RequiredArgsConstructor
public class GoalController {
    private final GoalService goalService;

    @GetMapping(value = "/{goalId}", produces = APPLICATION_JSON_VALUE)
    public Goal findByGoalId(@PathVariable("goalId") Long goalId) {
        return goalService.findByGoalId(goalId);
    }

    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public List<Goal> findAllGoals() {
        return goalService.findAllGoals();
    }

    @GetMapping(value = "count")
    public Long getGoalsCount() {
        return goalService.getGoalsCount();
    }

    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Goal addGoal(@RequestBody GoalDTO dto) {
        return goalService.addGoal(dto);
    }

    @PatchMapping(value = "/update/{goalId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Goal updateGoal(@PathVariable("goalId") Long goalId, @RequestBody GoalDTO dto) {
        return goalService.updateGoal(goalId, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/delete/{goalId}", produces = APPLICATION_JSON_VALUE)
    public void deleteGoal(@PathVariable("goalId") Long goalId) {
        goalService.deleteGoal(goalId);
    }
}
