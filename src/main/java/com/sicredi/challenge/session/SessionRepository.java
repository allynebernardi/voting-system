package com.sicredi.challenge.session;

import com.sicredi.challenge.associate.Associate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SessionRepository extends ReactiveMongoRepository<Session,Integer> {

}
