package com.coop.challenge.session;

import com.coop.challenge.agenda.Agenda;
import com.coop.challenge.associate.Associate;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SessionRepository extends ReactiveMongoRepository<Session, Integer> {

    Mono<Session> findByAssociateAndAgenda(Associate associate, Agenda agenda);

    @Aggregation("{ $group: { _vote : $vote, names : { $addToSet : $agenda} } }")
    Flux<Session> groupByVoteAndAgenda(Mono<Agenda> agenda);
}
