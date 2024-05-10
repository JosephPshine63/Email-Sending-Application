package dev.pioruocco.email.repository;

import dev.pioruocco.email.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //ignoriamo lettere maiuscole o minuscole
    User findByEmailIgnoreCase(String email);

    Boolean existsByEmail(String email);

}
