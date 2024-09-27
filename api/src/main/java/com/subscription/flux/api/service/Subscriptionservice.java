package com.subscription.flux.api.service;

import com.subscription.flux.api.dto.Subscriptiondto;
import com.subscription.flux.api.model.Registration;
import com.subscription.flux.api.model.Subscription;
import com.subscription.flux.api.repo.RegistrationRepo;
import com.subscription.flux.api.repo.Subscriptionrepo;
import com.subscription.flux.api.vo.SubscriptionRequest;
import com.subscription.flux.api.vo.SubscriptionResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

import static com.mysql.cj.conf.PropertyKey.logger;

@Slf4j
@Service
public class Subscriptionservice {

    @Autowired
    private Subscriptionrepo subscriptionrepo;

    @Autowired
    private RegistrationRepo registrationRepo;

    // Method to retrieve all subscriptions and map them to DTOs
    public Flux<Subscriptiondto> findAllSubscriptions() {
        return Flux.fromIterable(subscriptionrepo.findAll())
                .map(subscription -> new Subscriptiondto(subscription.getSubscriptionId()));
    }

    public Mono<SubscriptionResponse> saveSubscription(SubscriptionRequest subscriptionRequest) {
        return Mono.fromCallable(() -> registrationRepo.findById(subscriptionRequest.getRegistrationId())
                        .orElseThrow(() -> new EntityNotFoundException("Registration not found")))
                .flatMap(registration -> {
                    Subscription subscription = new Subscription();
                    subscription.setSubscriptionId(UUID.randomUUID().toString());
                    subscription.setEvent(subscriptionRequest.getEvent());
                    subscription.setCallbackUrl(subscriptionRequest.getCallbackUrl());
                    subscription.setMatchKey(subscriptionRequest.getKey());
                    subscription.setMatchValue(String.join(",", subscriptionRequest.getValues()));
                    subscription.setTrackId(UUID.randomUUID().toString());
                    subscription.setAccountNumber("defaultAccountNumber");
                    subscription.setSubscriptionDatetime(new Date());
                    subscription.setRegistration(registration);

                    return Mono.fromCallable(() -> subscriptionrepo.save(subscription))
                            .map(savedSubscription -> new SubscriptionResponse(savedSubscription));
                });
    }

}
