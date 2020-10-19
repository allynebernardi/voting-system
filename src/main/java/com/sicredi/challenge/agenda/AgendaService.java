package com.sicredi.challenge.agenda;

import com.sicredi.challenge.exceptions.AgendaException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
@Slf4j
public class AgendaService {


    @Autowired
    AgendaRepository agendaRepository;

 
    public Flux<Agenda> findAll(Pageable pageable){
        return agendaRepository.findAll();
    }

    public Mono<Agenda> findById(Integer id){
        return agendaRepository.findById(id)
                .switchIfEmpty(AgendaNotFound(id));
    }

    public Mono<Agenda> save(Agenda entity){
        return agendaRepository.save(entity);
    }

    public void delete(Integer id){
       Mono<Agenda> agendaMono = findById(id);
       agendaMono.switchIfEmpty(AgendaNotFound(id))
            .flatMap(agenda -> agendaRepository.delete(agenda));
    }

    private Mono<Agenda> AgendaNotFound(Integer id) {
        return Mono.error(new AgendaException("Agenda not found for id {}", id));
    }


}
