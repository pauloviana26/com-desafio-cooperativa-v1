package com.desafio.cooperativa.v1.service;

import com.desafio.cooperativa.v1.dtos.AssociadoDTO;
import com.desafio.cooperativa.v1.exceptions.BusinessException;

public interface AssociadoService {

    AssociadoDTO salvarAssociado(AssociadoDTO associadoDTO) throws BusinessException;


    AssociadoDTO buscarAssociado(String id, String email) throws BusinessException;
}
