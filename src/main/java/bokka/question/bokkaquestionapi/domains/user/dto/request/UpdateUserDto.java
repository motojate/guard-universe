package bokka.question.bokkaquestionapi.domains.user.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateUserDto {
    private String userSeq;
    private String name;
}
