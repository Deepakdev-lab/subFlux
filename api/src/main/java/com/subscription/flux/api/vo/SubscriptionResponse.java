package com.subscription.flux.api.vo;

import com.subscription.flux.api.model.Subscription;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubscriptionResponse {


    private boolean successful;

    private String errorCode;

    private String errorMessage;

    private List<String> subscriptionId;

    public SubscriptionResponse(Subscription savedSubscription) {
    }
}