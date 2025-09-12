package ir.dc.userAuthenticator.repository;

import ir.dc.userAuthenticator.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Override
    Optional<UserEntity> findById(Long aLong);

    Optional<UserEntity> findByUsername(String username);
}
