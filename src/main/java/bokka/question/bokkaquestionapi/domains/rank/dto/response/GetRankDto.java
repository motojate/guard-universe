package bokka.question.bokkaquestionapi.domains.rank.dto.response;

import bokka.question.bokkaquestionapi.common.enums.Tier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetRankDto {
    private int score;
    private Tier tier;


    public GetRankDto(int score, Tier tier) {
        this.score = score;
        this.tier = tier;
    }

}
