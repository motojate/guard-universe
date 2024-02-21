package bokka.question.bokkaquestionapi.domains.rank.repository;
import bokka.question.bokkaquestionapi.common.enums.Tier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
    @Query("SELECT r FROM Rank r WHERE r.user.userSeq = :userSeq")
    Rank findByUserSeq(@Param("userSeq") String userSeq);

    @Query("SELECT r FROM Rank r JOIN FETCH r.user WHERE r.rankTier.tier = :tier")
    List<Rank> findByTier(@Param("tier") Tier tier);

}
