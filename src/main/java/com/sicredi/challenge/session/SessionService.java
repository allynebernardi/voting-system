package com.sicredi.challenge.session;

import com.sicredi.challenge.associate.UserClientService;
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


    public static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";
    public static final String SESSION_EXPIRED = "Session  Expired";
    public static final String ASSOCIATE_ALREADY_VOTED = "Associate Already Voted";
    public static final String ASSOCIATE_ENABLE_TO_VOTE = "Associate Enable to Vote";

    @Autowired
    SessionRepository sessionRepository;

    private final UserClientService userClientService;

    private SessionMapper sessionMapper;


    public Mono<Session> findById(Integer id) {
        return sessionRepository.findById(id)
                .switchIfEmpty(sessionException(HttpStatus.NOT_FOUND, SESSION_EXPIRED));
    }

    public Mono<SessionResponseDTO> save(Session entity) {
        log.info("Registring Session  And Vote for Agenda{}", entity.getAgenda().getTitle());
        return sessionRepository.findByAssociateAndAgenda(entity.getAssociate(), entity.getAgenda())
                .flatMap(alreadyVoted -> sessionException(HttpStatus.CONFLICT, ASSOCIATE_ALREADY_VOTED))
                .filterWhen(session -> isAbleToVote(entity))
                .then(this.sessionRepository.save(entity))
                .map(session -> sessionMapper.map(session));
    }

    private Mono<Boolean> isAbleToVote(Session session) {
        return userClientService.validateUser(session.getAssociate().getCpf())
                .map(s -> s.equals(ABLE_TO_VOTE) ? true : false)
                .map(exist -> !exist)
                .switchIfEmpty(sessionException(HttpStatus.PRECONDITION_FAILED, ASSOCIATE_ENABLE_TO_VOTE));
    }

    // TODO: 21/10/2020 implement logic to return sum votes of the Session  
    public Mono<SessionResponseDTO> findResultsByAgendaId(Integer agendaId) {
        return Mono.just(SessionResponseDTO.builder().build());
    }

    public <T> Mono<T> sessionException(HttpStatus httpStatus, String message) {
        return Mono.error(new ResponseStatusException(httpStatus, message));
    }


}
