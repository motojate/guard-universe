package bokka.question.bokkaquestionapi.domains.question.repository;
import bokka.question.bokkaquestionapi.domains.user.repository.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "BK_MB_QUESTION_LEARNING_MANAGE")
public class Manage {

    @EmbeddedId
    private ManageSeq manageSeq;

    private int score = 0;

    @Column(name = "retry_cnt")
    private int retryCount = 0;

    @Column(name = "is_show")
    private Boolean isShow = false;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @MapsId("userSeq")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;
}