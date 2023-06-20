package com.desafio.cooperativa.v1.service.impl;

import com.desafio.cooperativa.v1.domain.entidades.Associado;
import com.desafio.cooperativa.v1.domain.repositorios.AssociadoRepository;
import com.desafio.cooperativa.v1.dtos.AssociadoDTO;
import com.desafio.cooperativa.v1.exceptions.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssociadoServiceImplTest {

    @Mock
    AssociadoRepository associadoRepository;

    @InjectMocks
    AssociadoServiceImpl associadoService;

    private final String associadoId = "c2387b9e-6cc9-43b4-b8a1-6f8d3b89bfa0";

    private final String associadoEmail = "paulo@email.com";

    private final String associadoNome = "Paulo";

    @Test
    void salvarAssociado() throws BusinessException {
        when(associadoRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(associadoRepository.save(any(Associado.class))).thenReturn(buildAssociado());

        var resposta = associadoService.salvarAssociado(buildAssociadoDTO());

        verify(associadoRepository, times(1)).findByEmail(anyString());
        verify(associadoRepository, times(1)).save(any(Associado.class));
        assertNotNull(resposta);
        assertEquals(buildAssociadoDTO().getEmail(), resposta.getEmail());
        assertEquals(buildAssociadoDTO().getNome(), resposta.getNome());
        assertEquals(UUID.fromString(associadoId), resposta.getId());
    }

    @Test
    void salvarAssociadoException() {
        when(associadoRepository.findByEmail(anyString())).thenReturn(Optional.of(buildAssociado()));

        BusinessException exception = assertThrows(BusinessException.class, () -> associadoService.salvarAssociado(buildAssociadoDTO()));

        verify(associadoRepository, times(1)).findByEmail(anyString());
        assertNotNull(exception);
    }

    @Test
    void buscarAssociado() throws BusinessException {
        when(associadoRepository.findByIdOrEmail(any(), anyString())).thenReturn(Optional.of(buildAssociado()));

        var associado = associadoService.buscarAssociado(associadoId, buildAssociado().getEmail());

        verify(associadoRepository, times(1)).findByIdOrEmail(any(), anyString());
        assertNotNull(associado);
        assertEquals(UUID.fromString(associadoId), associado.getId());
        assertEquals(associadoEmail, associado.getEmail());
        assertEquals(associadoNome, associado.getNome());
    }

    @Test
    void buscarAssociadoException() throws BusinessException {
        when(associadoRepository.findByIdOrEmail(any(), anyString())).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> associadoService.buscarAssociado(associadoId, associadoEmail));

        verify(associadoRepository, times(1)).findByIdOrEmail(any(), anyString());
        assertNotNull(exception);
    }

    private AssociadoDTO buildAssociadoDTO() {
        return AssociadoDTO.builder()
                .email("paulo@email.com")
                .nome("Paulo")
                .build();
    }

    private Associado buildAssociado() {
        return Associado.builder()
                .id(UUID.fromString(associadoId))
                .email("paulo@email.com")
                .nome("Paulo")
                .build();
    }
}