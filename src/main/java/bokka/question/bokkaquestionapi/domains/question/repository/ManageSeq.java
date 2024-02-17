package bokka.question.bokkaquestionapi.domains.question.repository;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ManageSeq implements Serializable {
    private String userSeq;
    private String questionSeq;
}