package SecureBack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import SecureBack.entity.User;
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query("update User u set u.email = ?1 where u.id = ?2")
    void updateUserEmail(String email, Long id);
}