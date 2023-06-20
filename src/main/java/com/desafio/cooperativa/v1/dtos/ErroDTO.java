package com.desafio.cooperativa.v1.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErroDTO {
    private String mensagem;
    private List<ErroValidacaoDTO> errosValidacao = new ArrayList<>();

    public void adicionarErros(String campo, String mensagem) {
        var erroValidacao = ErroValidacaoDTO.builder()
                .campo(campo)
                .mensagem(mensagem)
                .build();
        errosValidacao.add(erroValidacao);
    }
}
