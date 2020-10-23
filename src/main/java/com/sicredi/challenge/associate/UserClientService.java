package com.sicredi.challenge.associate;


import com.sicredi.challenge.config.clients.UserApiConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.retry.RetryExhaustedException;
import reactor.util.function.Tuple2;

@Service
@AllArgsConstructor
@Getter
@Slf4j
public class UserClientService {

    private UserApiConfig userApiConfig;

    @Qualifier("userClientConfig")
    private WebClient userClientConfig;

    public Mono<String> validateUser(String cpf){
        return getUserClientConfig()
                .get()
                .uri(userApiConfig.getValidateUser(), cpf)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, e -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,"Associate not found")))
                .bodyToMono(String.class)
                .retry(3)
                .doOnError(RetryExhaustedException.class, e -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Timeout while calling user Api");
                })
                .elapsed()
                .doOnNext(tuple -> log.trace("Milliseconds for retrinving userApiConfig - {}",tuple.getT1()))
                .map(Tuple2::getT2);
    }

}
