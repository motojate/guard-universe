package bokka.question.bokkaquestionapi.domains.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserName {
    private String name;
}
