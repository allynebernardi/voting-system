package com.coop.challenge.agenda;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AgendaRepository extends ReactiveMongoRepository<Agenda,Integer> {

   }
