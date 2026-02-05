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


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDTO userDTO) {
        try{
            userRepository.findByUsername(userDTO.getUsername()).ifPresent(u -> {
                throw new RuntimeException("Username already exists");
            });
            userRepository.findByEmail(userDTO.getEmail()).ifPresent(u -> {
                throw new RuntimeException("Email already exists");
            });
            boolean equals = userDTO.getPassword().equals(userDTO.getConfirmPassword());

            if (!equals){
                throw new RuntimeException("Passwords do not match");
            }else {
                String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
                userDTO.setPassword(encodedPassword);
                String encodedConformPassword = passwordEncoder.encode(userDTO.getConfirmPassword());
                userDTO.setPassword(encodedConformPassword);
                userDTO.setRole(Role.CUSTOMER);
                UserEntity save = modelMapper.map(userDTO, UserEntity.class);
                userRepository.save(save);
            }
        } catch (Exception e){
            throw new RuntimeException("Error creating user: " + e.getMessage());
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDTO.class))
                .toList();
    }
}
