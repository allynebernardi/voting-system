package com.sicredi.challenge.associate;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AssociateRepository extends ReactiveMongoRepository<Associate,Integer> {

}
