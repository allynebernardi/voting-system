package com.coop.challenge.session;


import com.coop.challenge.config.swagger.ApiDefaultResponses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequestMapping("/v1/sessions")
@RestController
@Api("Session")
public class SessionController {

    @Autowired
    SessionService sessionService;



    @ApiOperation(value = "Create an new Session")
    @ApiDefaultResponses
    @PostMapping
    public ResponseEntity<Mono<SessionResponseDTO>> createAgenda(@Validated @RequestBody Session session){
        return  ResponseEntity.ok(sessionService.save(session));
    }

    @ApiOperation(value = "Find Results by Agenda")
    @ApiDefaultResponses
    @GetMapping(value = "/result/{agendaId}")
    public ResponseEntity<Flux<SessionResponseDTO>> findSessionResultsByAgenda(@PathVariable("agendaId") Integer agendaId) {
        return ResponseEntity.ok(sessionService.findResultsByAgendaId(agendaId));
    }

}
