package bokka.question.bokkaquestionapi.domains.rank.controller;

import bokka.question.bokkaquestionapi.common.response.BaseResponse;
import bokka.question.bokkaquestionapi.domains.rank.dto.response.GetRankDto;
import bokka.question.bokkaquestionapi.domains.rank.repository.Rank;
import bokka.question.bokkaquestionapi.domains.rank.service.RankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "rank")
public class RankController {
    private final RankService rankService;
    @GetMapping()
    public ResponseEntity<BaseResponse<GetRankDto>> findRankByUserSeq(@RequestAttribute("userSeq") String userSeq) {
        GetRankDto result = rankService.findByUserRank(userSeq);
        BaseResponse<GetRankDto> response = BaseResponse.success(result);
        return ResponseEntity.ok(response);
    }
}
