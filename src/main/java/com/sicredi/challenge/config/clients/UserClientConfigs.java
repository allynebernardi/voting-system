package com.sicredi.challenge.config.clients;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Configuration
@AllArgsConstructor
public class UserClientConfigs {

    private final UserApiConfig userApiConfig;

    @Bean(name = "userClientConfig")
    public WebClient userClientConfig(){
        return WebClient
                .builder()
                .baseUrl(userApiConfig.getUserURL())
                .clientConnector(reactorTcpConfig(userApiConfig.getConnectTimeout(),userApiConfig.getReadTimeout()))
                .build();
    }

    private static ClientHttpConnector reactorTcpConfig(int connectTimeout, int readTimeout) {
        return new ReactorClientHttpConnector(HttpClient.from(TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout*1000)
                .doOnConnected(
                        c -> c.addHandlerLast(new ReadTimeoutHandler(readTimeout))
                )).compress(true));

    }
}
