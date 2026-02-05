package ecom.study.controller;


import ecom.study.model.dto.UserDTO;
import ecom.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<Void> CreateUser(UserDTO userDTO){
        userService.createUser(userDTO);
        return ResponseEntity.ok().build();
    }
}
