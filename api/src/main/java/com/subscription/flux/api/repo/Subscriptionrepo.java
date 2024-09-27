package com.subscription.flux.api.repo;

import com.subscription.flux.api.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Subscriptionrepo extends JpaRepository<Subscription,String> {
}
