package com.sicredi.challenge.associate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@AllArgsConstructor
@Builder
@Document
public class Associate {

    @Id
    private Integer id;

    private String name;

    private String cpf;

    private boolean active;

 }
