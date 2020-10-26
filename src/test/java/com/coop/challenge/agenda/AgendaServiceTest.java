package com.coop.challenge.agenda;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {AgendaService.class})
class AgendaServiceTest {

    public static final int MOCK_VALID_ID = 1;
    public static final String MOCK_TITLE = "Test Title Agenda";
    public static final String MOCK_DESCRIPTION = "Description Test";
    public static final int MOCK_INVALID_ID = -50;
    @InjectMocks
    AgendaService agendaService;

    @Mock
    AgendaRepository agendaRepository;

    @Test
    public void givenAnAgendaIdExists_whenFindAgendaById_shoulReturnAgendaMono(){

        var agendaMock = Agenda.builder()
                .id(MOCK_VALID_ID)
                .title(MOCK_TITLE)
                .date(new Date())
                .description(MOCK_DESCRIPTION)
                .build();
        when(agendaRepository.findById(MOCK_VALID_ID)).thenReturn(Mono.just(Agenda.builder().build()));
        Mono<Agenda> agendaMono = agendaService.findById(MOCK_VALID_ID);
        StepVerifier.create(agendaMono)
                .expectNextMatches(agenda ->agenda.getId().equals(agendaMock.getId()) );
    }


    @Test
    public void givenAValidPaylod_whenSave_shoulReturnAgendaMonoCreated(){

        var agendaMock = Agenda.builder()
                .id(MOCK_VALID_ID)
                .title(MOCK_TITLE)
                .date(new Date())
                .description(MOCK_DESCRIPTION)
                .build();
        when(agendaRepository.save(agendaMock)).thenReturn(Mono.just(agendaMock));

        Mono<Agenda> agendaMono = agendaService.save(agendaMock);
        StepVerifier.create(agendaMono)
                .expectNext(agendaMock)
                .expectComplete()
                .verify();
        verify(agendaRepository,times(1)).save(any(Agenda.class));

    }

    @Test
    public void givenAnInvalidAgendId_whenFindById_thenThrowAgendaNotFoundException(){
        when(agendaRepository.findById(anyInt())).thenReturn(Mono.empty());

        Mono<Agenda> agendaMono = agendaService.findById(MOCK_INVALID_ID);
        StepVerifier.create(agendaMono)
                .expectErrorMatches(throwable -> throwable instanceof ResponseStatusException)
                .verify();

    }

}