package com.coop.challenge.associate;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AssociateRepository extends ReactiveMongoRepository<Associate,Integer> {

    Mono<Associate> findByCpf(String cpf);
}
