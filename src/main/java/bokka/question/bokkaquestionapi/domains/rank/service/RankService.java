package bokka.question.bokkaquestionapi.domains.rank.service;

import bokka.question.bokkaquestionapi.domains.rank.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankService {
    private final RankRepository rankRepository;


}
