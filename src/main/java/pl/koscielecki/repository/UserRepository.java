package pl.koscielecki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.koscielecki.user.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    User findOneById(Long id);

    User findOneByEmail(String email);
}
