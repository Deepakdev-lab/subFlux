package com.subscription.flux.api.controller;

import com.subscription.flux.api.dto.Subscriptiondto;
import com.subscription.flux.api.service.Subscriptionservice;
import com.subscription.flux.api.vo.SubscriptionRequest;
import com.subscription.flux.api.vo.SubscriptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api-poc/subscription/")
public class SubscriptionController {

    @Autowired
    private Subscriptionservice subscriptionservice;


    @GetMapping("getAll")
    public Flux<Subscriptiondto> getAllSubscriptions() {
        return subscriptionservice.findAllSubscriptions();
    }

    @PostMapping(value = {"/subscribe"})
    public Mono<ResponseEntity<SubscriptionResponse>> subscribe(
            @Valid @RequestBody SubscriptionRequest subscriptionRequest,
            @RequestParam(required = false, defaultValue = "false") Boolean tokenFlag) {

        return subscriptionservice.saveSubscription(subscriptionRequest)
                .map(response -> {
                    if ("1".equals(response.getErrorCode()) && Boolean.TRUE.equals(response.isSuccessful())) {
                        // If there is an error code and response is marked successful (business logic failure)
                        response.setSuccessful(false);
                        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                    } else if ("1".equals(response.getErrorCode()) && Boolean.FALSE.equals(response.isSuccessful())) {
                        // If there is an error code and response is not successful
                        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                    } else {
                        // If the response is successful
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                })
                .onErrorResume(e -> {
                    // Handle errors during subscription processing
                    SubscriptionResponse errorResponse = new SubscriptionResponse();
                    errorResponse.setErrorCode("500");
                    errorResponse.setSuccessful(false);
                    return Mono.just(new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR));
                });
    }

}
