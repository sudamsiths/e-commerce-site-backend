package ecom.study.service.impl;

import ecom.study.model.dto.UserDTO;
import ecom.study.model.entity.UserEntity;
import ecom.study.model.enums.Role;
import ecom.study.repository.UserRepository;
import ecom.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDTO userDTO) {
        if (userDTO == null) {
            throw new RuntimeException("User data is required");
        }
        if (userDTO.getUsername() == null || userDTO.getEmail() == null) {
            throw new RuntimeException("Username and email are required");
        }

        userRepository.findByUsername(userDTO.getUsername()).ifPresent(u -> {
            throw new RuntimeException("Username already exists");
        });
        userRepository.findByEmail(userDTO.getEmail()).ifPresent(u -> {
            throw new RuntimeException("Email already exists");
        });

        if (!Objects.equals(userDTO.getPassword(), userDTO.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match or are null");
        }

        String encoded = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encoded);
        userDTO.setConfirmPassword(null); // don't store confirm password
        userDTO.setRole(Role.CUSTOMER);

        UserEntity save = modelMapper.map(userDTO, UserEntity.class);
        userRepository.save(save);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDTO.class))
                .toList();
    }

   @Override
    public String signIn(String email, String password) {
    if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
        throw new IllegalArgumentException("Email and password are required");
    }

    UserEntity userEntity = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Invalid email or password"));

    if (!passwordEncoder.matches(password, userEntity.getPassword())) {
        throw new RuntimeException("Invalid email or password");
    }

    return "User signed in successfully";
}
}
