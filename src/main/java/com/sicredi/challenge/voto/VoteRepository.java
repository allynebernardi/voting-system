package com.sicredi.challenge.voto;

import com.sicredi.challenge.session.Session;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VoteRepository extends ReactiveMongoRepository<Vote,Integer> {

}
