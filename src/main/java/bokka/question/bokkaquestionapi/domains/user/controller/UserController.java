package bokka.question.bokkaquestionapi.domains.user.controller;

import bokka.question.bokkaquestionapi.domains.user.dto.request.CreateUserDto;
import bokka.question.bokkaquestionapi.domains.user.repository.User;
import bokka.question.bokkaquestionapi.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("create")
    public ResponseEntity<User> saveUser(@RequestBody CreateUserDto createUserDto) {
        userService.saveUser(createUserDto);
        return ResponseEntity.ok().build();
    }
}
