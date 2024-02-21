package bokka.question.bokkaquestionapi.domains.rank.service;

import bokka.question.bokkaquestionapi.domains.rank.dto.response.GetRankDto;
import bokka.question.bokkaquestionapi.domains.rank.repository.Rank;
import bokka.question.bokkaquestionapi.domains.rank.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class RankService {
    private final RankRepository rankRepository;

    public GetRankDto findByUserRank(String userSeq) {
        return rankRepository.findByUserSeq(userSeq);
    }

}
