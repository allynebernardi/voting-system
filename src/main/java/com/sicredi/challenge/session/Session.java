package com.sicredi.challenge.session;


import com.sicredi.challenge.agenda.Agenda;
import com.sicredi.challenge.associate.Associate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@AllArgsConstructor
@Builder
@Document
public class Session {

    @Id
    private Integer id;

    @DBRef(lazy = true)
    private Agenda agenda;

    @DBRef(lazy = true)
    private Associate associate;

    private boolean VoteStatus;

 }
