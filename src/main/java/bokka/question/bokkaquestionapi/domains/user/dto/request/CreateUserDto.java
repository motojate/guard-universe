package bokka.question.bokkaquestionapi.domains.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class CreateUserDto {
    private String userSeq;
    private String name;
}
