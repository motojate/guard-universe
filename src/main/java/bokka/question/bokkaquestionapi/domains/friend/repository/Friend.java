package bokka.question.bokkaquestionapi.domains.friend.repository;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "BK_MB_FRIEND",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "friendSeq",
                        columnNames = {"friend_user_seq", "user_seq"}
                )
        })
@IdClass(FriendSeq.class)
public class Friend {

    @Id
    @Column(name = "friend_user_seq")
    private String friendUserSeq;

    @Id
    @Column(name = "user_seq")
    private String userSeq;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
