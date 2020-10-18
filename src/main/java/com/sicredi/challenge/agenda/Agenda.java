package com.sicredi.challenge.agenda;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class Agenda {

    String title;
    Date date;
    String description;

}
