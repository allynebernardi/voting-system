package com.sicredi.challenge.agenda;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@AllArgsConstructor
@Builder
@Document
public class Agenda {

    @Id
    private Integer id;

    private String title;

    private Date date;

    private String description;

 }
