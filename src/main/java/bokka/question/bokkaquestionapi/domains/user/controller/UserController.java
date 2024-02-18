package bokka.question.bokkaquestionapi.domains.user.controller;
import bokka.question.bokkaquestionapi.common.response.BaseResponse;
import bokka.question.bokkaquestionapi.domains.user.dto.request.UpdateUserDto;
import bokka.question.bokkaquestionapi.domains.user.dto.response.GetUserName;
import bokka.question.bokkaquestionapi.domains.user.repository.User;
import bokka.question.bokkaquestionapi.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "user")
public class UserController {

    private final UserService userService;

    @GetMapping("name")
    public ResponseEntity<BaseResponse<GetUserName>> findUserName(@RequestAttribute("userSeq") String userSeq) {
        String userName = userService.findUserName(userSeq);
        GetUserName getUserName = GetUserName.builder().name(userName).build();
        BaseResponse<GetUserName> response = BaseResponse.<GetUserName>builder().result(getUserName).code(200).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("name")
    public ResponseEntity<BaseResponse<Void>> updateUserName(@RequestAttribute("userSeq") String userSeq, @RequestBody Map<String, String> updateUserNameDto) {
        UpdateUserDto updateUserDto = UpdateUserDto.builder().userSeq(userSeq).name(updateUserNameDto.get("name")).build();
        userService.updateUserName(updateUserDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("create")
    public ResponseEntity<User> saveUser(@RequestAttribute("userSeq") String userSeq) {
        userService.saveUser(userSeq);
        return ResponseEntity.ok().build();
    }
}
