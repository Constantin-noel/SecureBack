package SecureBack.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import SecureBack.dto.CreateUserDto;
import SecureBack.entity.User;
import SecureBack.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository repository;

    public ResponseEntity<User> createUser(CreateUserDto dto) {
        List<User> users = repository.findAll();
        boolean isEmailUnique = true;
        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(users.get(i).getEmail(), dto.getEmail())) {
                isEmailUnique = false;
            }
        }

        if (!isEmailUnique) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User user = new User()
                .setName(dto.getName())
                .setSurname(dto.getSurname())
                .setPassword(dto.getPassword())
                .setEmail(dto.getEmail())
                .setPhone(dto.getPhone())
                .setAge(dto.getAge());
        user = repository.save(user);

        log.info("New user with id = {} created successfully!", user.getId());
        return ResponseEntity.ok(user);
    }


    public ResponseEntity<User> findUserById(Long id) {
        // todo: переделать метод, сейчас просто возвращается статус ACCEPTED
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


    public ResponseEntity<User> updateUserById(String name, String surname, String phone, String email, String password, int age, Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            log.error("User with id = {} not found.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        user.get()
                .setName(name)
                .setSurname(surname)
                .setPhone(phone)
                .setEmail(email)
                .setPassword(password)
                .setAge(age);

        repository.save(user.get());
        return ResponseEntity.ok(user.get());
    }

    @Transactional
    public ResponseEntity<User> updateUserEmailById(Long id, String email) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            log.error("User with id = {} not found.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        repository.updateUserEmail(email, id);
        user.get().setEmail(email);
        return ResponseEntity.ok(user.get());
    }


    public ResponseEntity<User> findUserByEmailAndPassword(String email, String password) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
