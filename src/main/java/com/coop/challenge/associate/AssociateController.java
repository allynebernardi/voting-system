package com.coop.challenge.associate;


import com.coop.challenge.config.swagger.ApiDefaultResponses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequestMapping("/v1/associates")
@RestController
@Api("Associates")
public class AssociateController {

    @Autowired
    AssociateService associateService;


    @ApiOperation(value = "Create an new Associate")
    @ApiDefaultResponses
    @PostMapping
    public ResponseEntity<Mono<Associate>> createAgenda(@Validated @RequestBody Associate associate){
        return ResponseEntity.ok(associateService.save(associate));
    }

    @ApiOperation(value = "Find all Associates")
    @ApiDefaultResponses
    @GetMapping
    public ResponseEntity<Flux<Associate>> findAll(){
        return ResponseEntity.ok(associateService.findAll());
    }

    @ApiOperation(value = "Find an Associate by Id")
    @ApiDefaultResponses
    @GetMapping(value="/{associateId}")
    public ResponseEntity<Mono<Associate>> findAgendaById(@PathVariable("associateId") Integer associateId){
        return ResponseEntity.ok(associateService.findById(associateId));
    }

    @ApiOperation(value = "Update Associate")
    @ApiDefaultResponses
    @PutMapping(value="/{associateId}")
    public ResponseEntity<Mono<Associate>> updateAgenda(@PathVariable Integer associateId, @RequestBody Associate associate){
             return ResponseEntity.ok(associateService.update(associateId,associate));
    }


}
