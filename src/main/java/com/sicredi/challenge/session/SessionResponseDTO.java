package com.sicredi.challenge.session;


import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SessionResponseDTO {

    private String agendaName;

    private String associateName;

    private boolean status;


}