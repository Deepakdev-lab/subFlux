package com.subscription.flux.api.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.sql.Timestamp;
import java.util.Map;

@Data
@Entity
@Table(name = "webhook_registrations")
public class Registration {
    @Id
    @Column(name = "registration_id", nullable = false)
    private String registrationId;

    @Column(name = "callback_url")
    private String callbackUrl;

    @Type(JsonType.class)
    @Column(name = "static_headers", columnDefinition = "json")
    private Map<String, String> staticHeaders;  // Use Map to represent JSON data

    @Column(name = "auth_type")
    private String authType;

    @Column(name = "auth_url")
    private String authUrl;

    @Column(name = "token_expiry")
    private int tokenExpiry;

    @Column(name = "signature_algorithm")
    private String signatureAlgorithm;

    @Column(name = "signature_secretkey")
    private String signatureSecretkey;

    @Column(name = "signature_headername")
    private String signatureHeaderName;

    @Column(name = "auth_client_id")
    private String authClientId;

    @Column(name = "auth_client_secret")
    private String authClientSecret;

    @Column(name = "param_name_client_id")
    private String paramNameClientId;

    @Column(name = "param_name_client_secret")
    private String paramNameClientSecret;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "auth_grant_type")
    private String authGrantType;

    @Column(name = "jwt_field_name")
    private String jwtFieldName;

    @Type(JsonType.class)
    @Column(name = "claims", columnDefinition = "json")
    private Map<String, String> claims;

    @Column(name = "param_name_token")
    private String paramNameToken;

    @Column(name = "grant_type")
    private String grantType;

    @Column(name = "param_name_grant_type")
    private String paramNameGrantType;

    @Column(name = "registration_datetime")
    private Timestamp registrationDatetime;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "email_id", nullable = true)
    private String emailId;
}
