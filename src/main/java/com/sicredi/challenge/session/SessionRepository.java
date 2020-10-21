package com.sicredi.challenge.session;

import com.sicredi.challenge.agenda.Agenda;
import com.sicredi.challenge.associate.Associate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface SessionRepository extends ReactiveMongoRepository<Session,Integer> {

    Mono<Session> findByAssociateAndAgenda(Associate associate, Agenda agenda);
}
