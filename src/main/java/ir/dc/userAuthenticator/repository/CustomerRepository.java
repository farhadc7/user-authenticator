package ir.dc.userAuthenticator.repository;

import ir.dc.userAuthenticator.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByNationalCode(String nationalCode);

    Optional<CustomerEntity> findByUniqueCode(String uniqueCode);
}
