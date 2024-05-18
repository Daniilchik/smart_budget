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
                        .password(dto.getPassword())
                        .role(dto.getRole())
                        .build()
                );
    }

    @NotNull
    @Transactional
    public User updateUser(@NotNull Long userId, @NotNull UserDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found."));

        if(dto.getUsername() != null) user.setName(dto.getUsername());
        if(dto.getEmail() != null) user.setEmail(dto.getEmail());
        if(dto.getPassword() != null) user.setPassword(dto.getPassword());
        if(dto.getRole() != null) user.setRole(dto.getRole());

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(@NotNull Long userId) {
        userRepository.deleteById(userId);
    }
}
