package SecureBack.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import SecureBack.dto.CreateUserDto;
import SecureBack.entity.User;
import SecureBack.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    public final UserService service;

    @PostMapping("user")
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto dto) {
        return service.createUser(dto);
    }

    @GetMapping("get/user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        return service.findUserById(id);
    }

    @PutMapping("user/edit/{id}")
    public ResponseEntity<User> updateUserById(@RequestParam("name") String name,
                                               @RequestParam("surname") String surname,
                                               @RequestParam("phone") String phone,
                                               @RequestParam("email") String email,
                                               @RequestParam("password") String password,
                                               @RequestParam("age") int age,
                                               @PathVariable Long id) {
        return service.updateUserById(name, surname, phone, email, password, age, id);
    }

    @PutMapping("user/edit/email/{id}")
    public ResponseEntity<User> updateUserEmailById(@PathVariable Long id, @RequestParam("email") String email) {
        return service.updateUserEmailById(id, email);
    }

    @GetMapping("get/user/{email}/{password}")
    public ResponseEntity<User> findUserByEmailAndPassword(@PathVariable String email,
                                                           @PathVariable String password) {
        return service.findUserByEmailAndPassword(email, password);
    }
}
