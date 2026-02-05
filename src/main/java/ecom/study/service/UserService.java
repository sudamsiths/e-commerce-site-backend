package ecom.study.service;

import ecom.study.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    void createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();
}
