package com.coop.challenge.agenda;


import com.coop.challenge.config.swagger.ApiDefaultResponses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Flux<Agenda>> findAll( ){
        return ResponseEntity.ok(agendaService.findAll());
    }

    @ApiOperation(value = "Find an agenda by Id")
    @ApiDefaultResponses
    @GetMapping(value="/{agendaId}")
    public ResponseEntity<Mono<Agenda>> findAgendaById(@PathVariable("agendaId") Integer agendaId){
        return ResponseEntity.ok(agendaService.findById(agendaId));
    }

    @ApiOperation(value = "Delete Agenda by Id")
    @ApiDefaultResponses
    @DeleteMapping(value="/{agendaId}")
    public ResponseEntity deleteAgenda(@PathVariable("agendaId") Integer agendaId){
        agendaService.delete(agendaId);
        return ResponseEntity.noContent().build();
    }
    @ApiOperation(value = "Update Agenda")
    @ApiDefaultResponses
    @PutMapping(value="/{agendaId}")
    public ResponseEntity<Mono<Agenda>> updateAgenda(@PathVariable Integer agendaId, @RequestBody Agenda agenda){
           return ResponseEntity.ok(agendaService.update(agendaId,agenda));
    }


}
