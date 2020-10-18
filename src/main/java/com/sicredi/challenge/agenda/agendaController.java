package com.sicredi.challenge.agenda;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/v1/agenda")
@RestController
@Api("Agenda")
public class agendaController {


    @ApiOperation(value = "Create an new agenda")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 400, message = "Invalid Parameters"),
            @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Agenda> createAgenda(@Validated @RequestBody Agenda agenda){
        return Mono.empty();
    }

}
