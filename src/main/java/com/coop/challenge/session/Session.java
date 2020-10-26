package com.coop.challenge.session;


import com.coop.challenge.agenda.Agenda;
import com.coop.challenge.associate.Associate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@Builder
@Document
public class Session {

    @Id
    private Integer id;

    private Agenda agenda;

    private Associate associate;

    private String vote;

}
