package bokka.question.bokkaquestionapi.domains.rank.repository;

import bokka.question.bokkaquestionapi.domains.user.repository.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@ToString
@Table(name = "BK_MB_RANK")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rank {

    @Builder
    public Rank(RankTier rankTier) {
        this.rankTier = rankTier;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "score")
    private int score = 0;

    @Column(name = "re_birth")
    private int reBirth = 0;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tier")
    private RankTier rankTier;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_seq")
    private User user;

    public void initUserRank(User user) {
        this.user = user;
    }

}
