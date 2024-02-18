package bokka.question.bokkaquestionapi.domains.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u.name FROM User u WHERE u.userSeq = :userSeq")
    Optional<String> findUserNameByUserSeq(@Param("userSeq") String userSeq);
}
