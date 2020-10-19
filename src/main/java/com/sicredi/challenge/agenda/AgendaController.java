package com.sicredi.challenge.agenda;


import com.sicredi.challenge.config.swagger.ApiDefaultResponses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequestMapping("/v1/agendas")
@RestController
@Api("Agenda")
public class AgendaController {

    @Autowired
    AgendaService agendaService;


    @ApiOperation(value = "Create an new agenda")
    @ApiDefaultResponses
    @PostMapping
    public ResponseEntity<Mono<Agenda>> createAgenda(@Validated @RequestBody Agenda agenda){
        return ResponseEntity.ok(agendaService.save(agenda));
    }

    @ApiOperation(value = "Find all agendas")
    @ApiDefaultResponses
    @GetMapping
    public ResponseEntity<Flux<Agenda>> findAll(Pageable pageable){
        return ResponseEntity.ok(agendaService.findAll(pageable));
    }

    @ApiOperation(value = "Find an agenda by Id")
    @ApiDefaultResponses
    @GetMapping(value="/{agendaId}")
    public ResponseEntity<Mono<Agenda>> findById(@PathVariable("agendaId") Integer agendaId){
        return ResponseEntity.ok(agendaService.findById(agendaId));
    }

}
