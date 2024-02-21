package bokka.question.bokkaquestionapi.domains.rank.service;

import bokka.question.bokkaquestionapi.domains.rank.dto.response.GetRankDto;
import bokka.question.bokkaquestionapi.domains.rank.repository.Rank;
import bokka.question.bokkaquestionapi.domains.rank.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class RankService {
    private final RankRepository rankRepository;

    public GetRankDto findByUserRank(String userSeq) {
        Rank userRank = rankRepository.findByUserSeq(userSeq);
        List<Rank> rankListByUserTierRange = rankRepository.findByTier(userRank.getRankTier().getTier());
        List<GetRankDto.RankInfoAndUserName> rankInfoAndUserNameList = new ArrayList<>();
        for (Rank rank : rankListByUserTierRange) {
            GetRankDto.RankInfo rankInfo = GetRankDto.RankInfo.builder().score(rank.getScore()).reBirth(rank.getScore()).tier(rank.getRankTier().getTier()).build();
            String userName = rank.getUser().getName();

            GetRankDto.RankInfoAndUserName rankInfoAndUserName = GetRankDto.RankInfoAndUserName.builder().rankInfo(rankInfo).userName(userName).build();
            rankInfoAndUserNameList.add(rankInfoAndUserName);
        }

        return GetRankDto.builder().rankInfo(GetRankDto.RankInfo.builder().tier(userRank.getRankTier().getTier()).reBirth(userRank.getReBirth()).score(userRank.getScore()).build()).rankList(rankInfoAndUserNameList).build();
    }

}
