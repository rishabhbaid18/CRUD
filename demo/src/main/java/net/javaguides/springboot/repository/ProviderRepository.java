package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Provider findByProviderName(String providerName);
}
