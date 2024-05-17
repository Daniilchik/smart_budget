package org.parnassolutions.Services;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.parnassolutions.DTOs.UserDTO;
import org.parnassolutions.Entities.User;
import org.parnassolutions.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @NotNull
    @Transactional
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @NotNull
    @Transactional
    public User createUser(@NotNull UserDTO dto) {
        return userRepository.save(
                User.builder()
                        .username(dto.getName())
                        .password(dto.getPassword())
                        .build()
        );
    }

    @Transactional
    public void deleteUser(@NotNull Integer id) {
        userRepository.deleteById(id);
    }
}
