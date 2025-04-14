package ua.kpi.jakartaee.service;

import jakarta.ejb.Local;

@Local
public interface Convertor<DTO, ENTITY> {
    DTO toDto(ENTITY entity) throws Exception;
    ENTITY toEntity(DTO dto) throws Exception;
}
