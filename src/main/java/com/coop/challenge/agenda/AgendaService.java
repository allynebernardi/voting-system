package com.coop.challenge.agenda;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
@Slf4j
public class AgendaService {


    @Autowired
    AgendaRepository agendaRepository;


    public Flux<Agenda> findAll() {
        return agendaRepository.findAll();
    }

    public Mono<Agenda> findById(Integer id) {
        return agendaRepository.findById(id)
                .switchIfEmpty(agendaNotFoundException());
    }

    public boolean existsAgenda(Integer id) {
        return agendaRepository.existsById(id)
                .map(aBoolean -> aBoolean).block();
    }

    public Mono<Agenda> save(Agenda entity) {
        return agendaRepository.save(entity);
    }

    // TODO: 21/10/2020 exception when id Not Exists 
    public void delete(Integer id) {
        agendaRepository.deleteById(id)
                .subscribe(response -> log.info("Agenda {} deleted", id),
                        error -> log.warn("Error to delete Agenda {}", id));

    }

    public Mono<Agenda> update(Integer id, Agenda agenda) {
        return agendaRepository.findById(id)
                .map(existingAgenda -> {
                    return buildAgenda(agenda, existingAgenda);
                })
                .flatMap(this.agendaRepository::save)
                .switchIfEmpty(agendaNotFoundException());
    }

    private Agenda buildAgenda(Agenda agenda, Agenda existingAgenda) {
        return existingAgenda.builder()
                .date(agenda.getDate())
                .id(existingAgenda.getId())
                .title(agenda.getTitle())
                .description(agenda.getDescription())
                .build();
    }


    public <T> Mono<T> agendaNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda not found"));
    }

}
