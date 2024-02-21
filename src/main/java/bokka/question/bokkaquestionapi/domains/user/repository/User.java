package bokka.question.bokkaquestionapi.domains.user.repository;

import bokka.question.bokkaquestionapi.domains.question.repository.Manage;
import bokka.question.bokkaquestionapi.domains.rank.repository.Rank;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Getter
@Table(name = "BK_MB_USER")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Builder
    public User(Rank rank, String userSeq, String name) {
        this.userSeq = userSeq;
        this.name = name;
        this.rank = rank;
        if(rank != null) rank.initUserRank(this);
    }

    public void updateUserName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "user_seq")
    private String userSeq;

    @Column(unique = true)
    private String name;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Rank rank;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Manage> manages = new ArrayList<>();

}
