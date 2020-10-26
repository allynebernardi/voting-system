package com.coop.challenge.config.clients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserApiConfig {

    @Value("${user-api.server-url}")
    private String userURL;

    @Value("${user.connect-timeout:20}")
    private Integer connectTimeout;

    @Value("${user.connect-timeout:20}")
    private Integer readTimeout;

    @Value("${user-api.validate-user}")
    private String validateUser;

}
