package com.desafio.cooperativa.v1.domain.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "pauta")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String titulo;

    private String descricao;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
    private List<Voto> votos = new ArrayList<>();
}
