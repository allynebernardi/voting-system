package com.sicredi.challenge.associate;

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


    @Autowired
    AssociateRepository associateRepository;


    public Flux<Associate> findAll() {
        return associateRepository.findAll();
    }

    public Mono<Associate> findById(Integer id) {
        return associateRepository.findById(id)
                .switchIfEmpty(associateNotFoundException());
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
                .switchIfEmpty(associateNotFoundException());
    }

    private Associate buildAssociate(Associate associate, Associate existingAssociate) {
        return existingAssociate.builder()
                .id(existingAssociate.getId())
                .active(associate.isActive())
                .cpf(associate.getCpf())
                .name(associate.getName())
                .build();
    }

    public <T> Mono<T> associateNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Associate not found"));
    }

}
