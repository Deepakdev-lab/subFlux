package com.subscription.flux.api.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubscriptionRequest {

    @NotEmpty(message = "Event cannot be empty")
    private String event;

    @NotEmpty(message = "Registration ID cannot be empty")
    private String registrationId;

    @NotEmpty(message = "Callback URL cannot be empty")
    private String callbackUrl;

    @NotEmpty(message = "Key cannot be empty")
    private String key;

    @Size(min = 1, max = 500, message = "Minimum 1 and Maximum 500 Match Keys allowed at a time")
    private List<String> values;
}
