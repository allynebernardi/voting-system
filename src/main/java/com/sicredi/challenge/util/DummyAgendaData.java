package com.sicredi.challenge.util;


import com.sicredi.challenge.agenda.Agenda;
import com.sicredi.challenge.agenda.AgendaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.Random;

//@Component
//public class DummyAgendaData implements CommandLineRunner{
//
//    public static final String DESCRIPTION = "Description for ";
//    private final AgendaRepository agendaRepository;
//
//    DummyAgendaData(AgendaRepository agendaRepository) {
//        this.agendaRepository = agendaRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        agendaRepository.deleteAll()
//                .thenMany(
//                        Flux.just("Agenda 1", "Agenda 2", "Agenda 3",
//                                "Agenda 4", "Agenda 5", "Agenda 6")
//                                .map(nome -> createAgenda(nome)  )
//                                .flatMap(agendaRepository::save))
//                .subscribe(System.out::println);
//   }
//
//   private Agenda createAgenda(String nome){
//       return Agenda.builder()
//               .id(new Random().nextInt(50))
//               .title(nome)
//               .date(new Date())
//               .description(DESCRIPTION+nome)
//               .build();
//   }
//}

