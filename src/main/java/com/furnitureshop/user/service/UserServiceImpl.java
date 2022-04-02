package com.furnitureshop.user.service;

import com.furnitureshop.user.dto.CreateUserDto;
import com.furnitureshop.user.dto.UserDto;
import com.furnitureshop.user.entity.User;
import com.furnitureshop.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        repository = userRepository;
        encoder = passwordEncoder;
    }

    @Override
    public List<UserDto> findAllDto() {
        return repository.findAllUserDto();
    }

    @Override
    public boolean isTakenUsername(String username) {
        return repository.countByUsername(username.toLowerCase()) >= 1;
    }

    @Override
    public boolean isTakenEmail(String email) {
        return repository.countByEmail(email) >= 1;
    }

    @Override
    public User createUser(CreateUserDto dto) {
        User newUser = new User();

        newUser.setUsername(dto.getUsername());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(encoder.encode(dto.getPassword())); // encode password  trước khi set

        return repository.save(newUser);
    }

}
