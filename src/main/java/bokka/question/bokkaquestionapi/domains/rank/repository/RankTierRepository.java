package bokka.question.bokkaquestionapi.domains.rank.repository;

import bokka.question.bokkaquestionapi.common.enums.Tier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankTierRepository extends JpaRepository<RankTier, Tier> {
}
