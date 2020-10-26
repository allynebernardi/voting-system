package com.coop.challenge.associate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


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
