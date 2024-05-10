package dev.pioruocco.email.repository;

import dev.pioruocco.email.domain.Confirmation;
import dev.pioruocco.email.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {

    Confirmation findByToken(String token);

}
