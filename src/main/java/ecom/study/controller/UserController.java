package ecom.study.controller;


import ecom.study.model.dto.UserDTO;
import ecom.study.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<Void> CreateUser( @Valid @RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/GetAll")
    public ResponseEntity<List<UserDTO>> GetAllUsers(){
        List<UserDTO> userDTOS = userService.getAllUsers();
        return ResponseEntity.ok(userDTOS);
    }
}
