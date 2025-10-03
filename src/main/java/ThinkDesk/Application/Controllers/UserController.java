package ThinkDesk.Application.Controllers;
import ThinkDesk.Application.DTOs.UserDto;
import ThinkDesk.Domain.Models.User;
import ThinkDesk.Domain.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService UserService;

    public UserController(UserService UserService) {
        this.UserService = UserService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok().body(UserService.getAll());
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return UserService.getById(id);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDto data) {
        return ResponseEntity.ok().body(UserService.create(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserDto data) {
        return ResponseEntity.ok().body(UserService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        UserService.delete(id);
        return ResponseEntity.noContent().build();
    }
}