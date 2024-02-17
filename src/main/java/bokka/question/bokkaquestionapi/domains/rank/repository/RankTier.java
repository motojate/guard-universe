package bokka.question.bokkaquestionapi.domains.rank.repository;

import bokka.question.bokkaquestionapi.common.enums.Tier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@ToString
@Table(name = "BK_MB_RANK_TIER")
public class RankTier {

    @Id
    @Enumerated(EnumType.STRING)
    private Tier tier;

    @Column(name = "tier_name")
    private String tierName;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "rankTier", cascade = CascadeType.ALL)
    private List<Rank> rank = new ArrayList<>();
}
