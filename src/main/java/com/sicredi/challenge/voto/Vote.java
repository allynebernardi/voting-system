package com.sicredi.challenge.voto;


import com.sicredi.challenge.session.Session;
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
public class Vote {

    @Id
    private Integer id;

    @DBRef(lazy = true)
    private Session session;

    private String value;

 }
