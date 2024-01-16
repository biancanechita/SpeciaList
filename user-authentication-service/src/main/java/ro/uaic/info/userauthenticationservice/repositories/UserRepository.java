package ro.uaic.info.userauthenticationservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.info.userauthenticationservice.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);
}
