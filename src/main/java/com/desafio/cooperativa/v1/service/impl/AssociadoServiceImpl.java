package com.desafio.cooperativa.v1.service.impl;

import com.desafio.cooperativa.v1.domain.repositorios.AssociadoRepository;
import com.desafio.cooperativa.v1.dtos.AssociadoDTO;
import com.desafio.cooperativa.v1.exceptions.BusinessException;
import com.desafio.cooperativa.v1.mappers.AssociadoMapper;
import com.desafio.cooperativa.v1.service.AssociadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

import static com.desafio.cooperativa.v1.mappers.AssociadoMapper.toAssociadoDTO;

@Service
@RequiredArgsConstructor
public class AssociadoServiceImpl implements AssociadoService {

    private final AssociadoRepository associadoRepository;

    @Override
    public AssociadoDTO salvarAssociado(AssociadoDTO associadoDTO) throws BusinessException {
        verificaAssociadoExiste(associadoDTO);
        var associado = associadoRepository.save(AssociadoMapper.toAssociado(associadoDTO));
        return toAssociadoDTO(associado);
    }

    @Override
    public AssociadoDTO buscarAssociado(String id, String email) throws BusinessException {
        UUID associadoId = null;
        if(Objects.nonNull(id))
            associadoId = converterStringParaUuid(id);
        var associado = associadoRepository.findByIdOrEmail(associadoId, email)
                .orElseThrow(() -> new BusinessException("Associado não encontrado por id e/ou e-mail.", HttpStatus.NOT_FOUND));
        return toAssociadoDTO(associado);
    }

    private UUID converterStringParaUuid(String id) {
        return UUID.fromString(id);
    }

    private void verificaAssociadoExiste(AssociadoDTO associadoDTO) throws BusinessException {
        var associado = associadoRepository.findByEmail(associadoDTO.getEmail());
        if(associado.isPresent()) {
            throw new BusinessException("Associado já é cadastrado.", HttpStatus.BAD_REQUEST);
        }
    }
}
