package com.subscription.flux.api.repo;

import com.subscription.flux.api.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepo extends JpaRepository<Registration, String> {
    Optional<Registration> findById(String registrationId);
}
