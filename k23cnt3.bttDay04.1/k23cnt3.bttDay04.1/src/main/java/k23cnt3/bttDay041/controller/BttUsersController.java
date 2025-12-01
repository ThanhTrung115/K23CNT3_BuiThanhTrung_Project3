package k23cnt3.bttDay041.controller;

import k23cnt3.bttDay041.dto.BttUsersDTO;
import k23cnt3.bttDay041.entity.BttUsers;
import k23cnt3.bttDay041.service.BttUsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class BttUsersController {
    @Autowired
    UsersService usersService;

    @GetMapping("/user-list")
    public List<Users> getAllUsers() {
        return usersService.findAll();
    }

    @PostMapping("/user-add")
    public ResponseEntity<String> addUser(@Valid @RequestBody UsersDTO user) {
        boolean result = usersService.create(user);
        if (result) {
            return ResponseEntity.ok("User created successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to create user");
        }
    }
}
