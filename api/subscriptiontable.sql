CREATE TABLE webhook_subscriptions (
    id VARCHAR(255) NOT NULL,
    subscription_id VARCHAR(255) NOT NULL,
    event_nm VARCHAR(255),
    registration_id VARCHAR(255) NOT NULL,
    callback_url VARCHAR(255),
    match_key VARCHAR(255),
    match_value VARCHAR(255),
    track_id VARCHAR(255),
    account_number VARCHAR(255),
    subscription_datetime DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (registration_id) REFERENCES webhook_registrations(registration_id)
);
