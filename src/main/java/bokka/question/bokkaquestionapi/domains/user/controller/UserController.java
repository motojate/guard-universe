package bokka.question.bokkaquestionapi.domains.user.controller;

import bokka.question.bokkaquestionapi.domains.user.dto.request.CreateUserDto;
import bokka.question.bokkaquestionapi.domains.user.repository.User;
import bokka.question.bokkaquestionapi.domains.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "user")
public class UserController {

    private final UserService userService;

    @GetMapping("name")
    public String findUserName(HttpServletRequest request) {
        String userSeq = (String) request.getAttribute("userSeq");
        return userService.findUserName(userSeq);
    }

    @PostMapping("create")
    public ResponseEntity<User> saveUser(@RequestBody CreateUserDto createUserDto) {
        userService.saveUser(createUserDto);
        return ResponseEntity.ok().build();
    }
}
