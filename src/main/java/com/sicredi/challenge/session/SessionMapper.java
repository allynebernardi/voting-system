package com.sicredi.challenge.session;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SessionMapper {

    @Mapping(target = "agendaName", source = "agenda.title")
    @Mapping(target = "associateName", source = "associate.name")
    SessionResponseDTO map (Session session);
}
