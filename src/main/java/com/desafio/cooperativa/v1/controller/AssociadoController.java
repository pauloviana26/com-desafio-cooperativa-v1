package com.desafio.cooperativa.v1.controller;

import com.desafio.cooperativa.v1.dtos.AssociadoDTO;
import com.desafio.cooperativa.v1.dtos.ErroDTO;
import com.desafio.cooperativa.v1.exceptions.BusinessException;
import com.desafio.cooperativa.v1.exceptions.CooperativaRunTimeException;
import com.desafio.cooperativa.v1.service.AssociadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/associados")
@Tag(name = "Associados")
@RequiredArgsConstructor
public class AssociadoController {

    private final AssociadoService associadoService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastra um novo associado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Associado cadastrado",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AssociadoDTO.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Associado já foi cadastrado",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))
                    }),
            @ApiResponse(responseCode = "500",
                    description = "Erro ao cadastrar o associado",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))
                    })
    })
    public ResponseEntity<AssociadoDTO> salvarAssociado(@Valid @RequestBody AssociadoDTO associadoDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(associadoService.salvarAssociado(associadoDTO));
        } catch (BusinessException e) {
            throw new CooperativaRunTimeException(e);
        }
    }

    @GetMapping
    @Operation(summary = "Busca associado por id e/ou por e-mail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Associado cadastrado",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AssociadoDTO.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Associado não foi encontrado",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErroDTO.class))
                    })
    })
    public ResponseEntity<AssociadoDTO> buscarAssociado(@RequestParam(required = false) String id, @RequestParam String email) {
        try {
            return ResponseEntity.ok().body(associadoService.buscarAssociado(id, email));
        } catch (BusinessException e) {
            throw new CooperativaRunTimeException(e);
        }
    }

}
