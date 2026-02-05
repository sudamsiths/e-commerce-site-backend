package ecom.study.repository;

import ecom.study.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<Object> findByUsername(String username);

    Optional<Object> findByEmail(String email);
}
