package com.subscription.flux.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "webhook_subscriptions")
@Data
public class Subscription {  // Corrected class name

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "subscription_id", nullable = false)
    private String subscriptionId;

    @Column(name = "event_nm")
    private String event;

    @ManyToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

    @Column(name = "callback_url")
    private String callbackUrl;

    @Column(name = "match_key")
    private String matchKey;

    @Column(name = "match_value")
    private String matchValue;

    @Column(name = "track_id")
    private String trackId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "subscription_datetime")
    private Date subscriptionDatetime;

    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString(); // or any other logic for ID generation
        }
    }
}
