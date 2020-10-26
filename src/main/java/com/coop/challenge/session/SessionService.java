package com.coop.challenge.session;

import com.coop.challenge.agenda.Agenda;
import com.coop.challenge.agenda.AgendaService;
import com.coop.challenge.associate.Associate;
import com.coop.challenge.associate.AssociateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class SessionService {


    public static final String SESSION_EXPIRED = "Session  Expired";
    public static final String ASSOCIATE_ALREADY_VOTED = "Associate Already Voted";
    public static final String AGENDA_NOT_FOUND = "Agenda Not Found";
    public static final String USER_NOT_ABLE_OR_EXIST = "User Not Able";
    public static final String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";


    @Autowired
    SessionRepository sessionRepository;

    private final AgendaService agendaService;

    private final AssociateService associateService;

    private SessionMapper sessionMapper;


    public Mono<Session> findById(Integer id) {
        return sessionRepository.findById(id)
                .switchIfEmpty(sessionException(HttpStatus.NOT_FOUND, SESSION_EXPIRED));
    }

    // TODO: 21/10/2020 Refactoring this logic and remove block().
    public Mono<SessionResponseDTO> save(Session entity) {
        if (!agendaService.existsAgenda(entity.getAgenda().getId())) {
            return sessionException(HttpStatus.PRECONDITION_FAILED, AGENDA_NOT_FOUND);
        } else if (associateService.validateAssociate(entity).equals(UNABLE_TO_VOTE)) {
            return sessionException(HttpStatus.PRECONDITION_FAILED, USER_NOT_ABLE_OR_EXIST);
        } else {
            log.info("Registring Session  And Vote for Agenda {}", entity.getAgenda().getId());
            return sessionRepository.findByAssociateAndAgenda(entity.getAssociate(), entity.getAgenda())
                    .flatMap(alreadyVoted -> sessionException(HttpStatus.CONFLICT, ASSOCIATE_ALREADY_VOTED))
                    .then(this.sessionRepository.save(entity))
                    .flatMap(sessionEnhance -> enhanceSessionResponse(sessionEnhance));
        }
    }

    // TODO: 21/10/2020 Refactoring or Remodeling Date Structure: MongoDb Reactive Does not support @DBRef yet. Created enhance to return more details.
    private Mono<SessionResponseDTO> enhanceSessionResponse(Session session) {
        return Mono.just(session)
                .flatMap(sessionAfter -> Mono.zip(Mono.just(session), agendaService.findById(sessionAfter.getId())
                        , associateService.findById(sessionAfter.getId())))
                .flatMap(this::getSessionEnhanced);

    }

    private Mono<SessionResponseDTO> getSessionEnhanced(Tuple3<Session, Agenda, Associate> tuple3) {
        return Mono.just(SessionResponseDTO
                .builder()
                .agendaName(tuple3.getT2().getTitle())
                .associateName(tuple3.getT3().getName())
                .voteStatus(StringUtils.isEmpty(tuple3.getT1().getVote()) ? false : true)
                .build());

    }

    // TODO: 23/10/2020 Finisher implematation 
    public Flux<SessionResponseDTO> findResultsByAgendaId(Integer agendaId) {
        return findVotesByAgenda(agendaId)
                .flatMap(this::enhanceSessionResponse);
    }

    private Mono<SessionResponseDTO> createResponse(String key, List<Session> sessions) {
        return Mono.just(SessionResponseDTO.builder().build());
    }

    private Flux<Session> findVotesByAgenda(Integer agendaId) {
        return sessionRepository.groupByVoteAndAgenda(agendaService.findById(agendaId));
    }

    public <T> Mono<T> sessionException(HttpStatus httpStatus, String message) {
        return Mono.error(new ResponseStatusException(httpStatus, message));
    }


}
