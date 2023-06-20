package com.desafio.cooperativa.v1.mappers;

import com.desafio.cooperativa.v1.domain.entidades.Associado;
import com.desafio.cooperativa.v1.dtos.AssociadoDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AssociadoMapper {

    public static Associado toAssociado(AssociadoDTO associadoDTO) {
        return Associado.builder()
                .email(associadoDTO.getEmail())
                .nome(associadoDTO.getNome())
                .build();
    }

    public static AssociadoDTO toAssociadoDTO(Associado associado) {
        return AssociadoDTO.builder()
                .id(associado.getId())
                .email(associado.getEmail())
                .nome(associado.getNome())
                .build();
    }
}
