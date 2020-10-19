package com.sicredi.challenge.agenda;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface AgendaRepository extends ReactiveMongoRepository<Agenda,Integer> {

   }
