package bokka.question.bokkaquestionapi.domains.rank.dto.response;

import bokka.question.bokkaquestionapi.common.enums.Tier;
import bokka.question.bokkaquestionapi.domains.rank.repository.Rank;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
public class GetRankDto {
    private final RankInfo userRank;
    private final List<RankInfoAndUserName> rankList;

    @Builder
    public GetRankDto(RankInfo rankInfo, List<RankInfoAndUserName> rankList) {
        this.userRank = rankInfo;
        this.rankList = rankList;

    }

    @Getter
    @Builder
    public static class RankInfo {
        private int score;
        private int reBirth;
        private Tier tier;
    }

    @Getter
    @Builder
    public static class RankInfoAndUserName {
        private RankInfo rankInfo;
        private String userName;
    }
}
