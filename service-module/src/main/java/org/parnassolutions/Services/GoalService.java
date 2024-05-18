package org.parnassolutions.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.parnassolutions.DTOs.GoalDTO;
import org.parnassolutions.DTOs.UserDTO;
import org.parnassolutions.Entities.Goal;
import org.parnassolutions.Repositories.GoalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalService {
    private final GoalRepository goalRepository;
    private final UserService userService;

    @NotNull
    @Transactional(readOnly = true)
    public Goal findByGoalId(@NotNull Long goalId) {
        return goalRepository.findById(goalId)
                .orElseThrow(() -> new EntityNotFoundException("Goal with id " + goalId + " not found."));
    }

    @NotNull
    @Transactional(readOnly = true)
    public List<Goal> findAllGoals() {
        return goalRepository.findAll();
    }

    @NotNull
    @Transactional(readOnly = true)
    public List<Goal> extractGoals(@NotNull UserDTO dto) {
        return dto.getGoals()
                .stream()
                .map(goalRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @NotNull
    @Transactional(readOnly = true)
    public Long getGoalCount() {
        return goalRepository.count();
    }

    @NotNull
    @Transactional
    public Goal addGoal(@NotNull GoalDTO dto) {
        return goalRepository.save(
                Goal.builder()
                        .user(userService.findByUserId(dto.getUserId()))
                        .total(dto.getTotal())
                        .balance(dto.getBalance())
                        .reachDate(dto.getReachDate())
                        .build()
        );
    }

    @NotNull
    @Transactional
    public Goal updateGoal(@NotNull Long goalId, @NotNull GoalDTO dto) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new EntityNotFoundException("Goal with id " + goalId + " not found."));

        if(dto.getUserId() != null) goal.setUser(userService.findByUserId(dto.getUserId()));
        if(dto.getTotal() != null) goal.setTotal(dto.getTotal());
        if(dto.getBalance() != null) goal.setBalance(dto.getBalance());
        if(dto.getReachDate() != null) goal.setReachDate(dto.getReachDate());

        return goalRepository.save(goal);
    }

    @Transactional
    public void deleteGoal(@NotNull Long goalId) {
        goalRepository.deleteById(goalId);
    }
}
