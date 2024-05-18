package org.parnassolutions.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.parnassolutions.DTOs.UserDTO;
import org.parnassolutions.Entities.User;
import org.parnassolutions.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /*private final AccountService accountService;
    private final GoalService goalService;*/

    @NotNull
    @Transactional(readOnly = true)
    public User findByUserId(@NotNull Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found."));
    }

    @NotNull
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @NotNull
    @Transactional(readOnly = true)
    public Long getUsersCount() {
        return userRepository.count();
    }

    @NotNull
    @Transactional
    public User addUser(@NotNull UserDTO dto) {
        return userRepository.save(
                User.builder()
                        .username(dto.getUsername())
                        .email(dto.getEmail())
                        //.accounts(accountService.extractAccounts(dto))
                        //.goals(goalService.extractGoals(dto))
                        .build()
                );
    }

    @NotNull
    @Transactional
    public User updateUser(@NotNull Long userId, @NotNull UserDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found."));

        if(dto.getUsername() != null) user.setUsername(dto.getUsername());
        if(dto.getEmail() != null) user.setEmail(dto.getEmail());
        //if(dto.getAccounts() != null) user.setAccounts(accountService.extractAccounts(dto));
        //if(dto.getGoals() != null) user.setGoals(goalService.extractGoals(dto));

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(@NotNull Long userId) {
        userRepository.deleteById(userId);
    }
}
