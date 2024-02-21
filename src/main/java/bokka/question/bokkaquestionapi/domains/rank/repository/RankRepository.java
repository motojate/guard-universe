package bokka.question.bokkaquestionapi.domains.rank.repository;

import bokka.question.bokkaquestionapi.domains.rank.dto.response.GetRankDto;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
    @Query("SELECT new bokka.question.bokkaquestionapi.domains.rank.dto.response.GetRankDto(r.score, r.rankTier.tier) From Rank r where r.user.userSeq = :userSeq")
    GetRankDto findByUserSeq(@Param("userSeq") String userSeq);

}
