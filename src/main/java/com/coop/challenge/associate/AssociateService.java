package com.coop.challenge.associate;

import com.coop.challenge.session.Session;
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
public class AssociateService {

    public static final String USER_NOT_FOUND = "User Not Found";
    public static final String ASSOCIATE_ENABLE_TO_VOTE = "Associate Enable to Vote";
    public static final String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";

    @Autowired
    AssociateRepository associateRepository;

    private final UserClientService userClientService;


    public Flux<Associate> findAll() {
        return associateRepository.findAll();
    }

    public Mono<Associate> findById(Integer id) {
        return associateRepository.findById(id)
                .switchIfEmpty(associateNotFoundException(HttpStatus.NOT_FOUND, USER_NOT_FOUND));
    }

    public Mono<Associate> save(Associate entity) {
        log.info("Creating Associate {}", entity);
        return associateRepository.save(entity);
    }

    public Mono<Associate> update(Integer id, Associate associate) {
        return associateRepository.findById(id)
                .map(existingAssociate -> {
                    return buildAssociate(associate, existingAssociate);
                })
                .flatMap(this.associateRepository::save)
                .switchIfEmpty(associateNotFoundException(HttpStatus.NOT_FOUND, USER_NOT_FOUND));
    }


    public String validateAssociate(Session session) {
        return associateRepository.findById(session.getAssociate().getId())
                .flatMap(associate -> userClientService.validateUser(associate.getCpf()))
                .switchIfEmpty(associateNotFoundException(HttpStatus.NOT_FOUND, USER_NOT_FOUND))
                .block();
    }

    private Associate buildAssociate(Associate associate, Associate existingAssociate) {
        return existingAssociate.builder()
                .id(existingAssociate.getId())
                .active(associate.isActive())
                .cpf(associate.getCpf())
                .name(associate.getName())
                .build();
    }

    public <T> Mono<T> associateNotFoundException(HttpStatus httpStatus, String message) {
        return Mono.error(new ResponseStatusException(httpStatus, message));
    }

}
