package com.sicredi.challenge.session;

import com.sicredi.challenge.voto.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
@Slf4j
public class SessionService {


    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    VoteRepository voteRepository;

    private SessionMapper sessionMapper;


    public Mono<Session> findById(Integer id){
        return sessionRepository.findById(id)
                .switchIfEmpty(sessionNotFoundException(id));
    }

    public Mono<SessionResponseDTO> save(Session entity){
        log.info("Registring Session  And Vote for Agenda{}",entity.getAgenda().getTitle());
        return sessionRepository.save(entity)
                .map(session -> sessionMapper.map(session))
                .switchIfEmpty(sessionExpiredException(entity.getId()));

    }

    public <T> Mono<T> sessionExpiredException(Integer id){
        return Mono.error(new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT,"Session  Expired"));
    }
    public <T> Mono<T> sessionNotFoundException(Integer id){
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,"Session  Expired"));
    }

    public Mono<SessionResponseDTO> findResultsByAgendaId(Integer agendaId) {
        return Mono.just(SessionResponseDTO.builder().build());
    }
}
