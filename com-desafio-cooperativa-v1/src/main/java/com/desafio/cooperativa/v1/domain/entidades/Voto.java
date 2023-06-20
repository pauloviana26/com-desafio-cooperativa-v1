package com.desafio.cooperativa.v1.domain.entidades;

import com.desafio.cooperativa.v1.enums.VotoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "voto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @Column(name = "associado_id")
    private UUID associadoId;

    @Enumerated(EnumType.STRING)
    @Column(name = "voto")
    private VotoEnum voto;
}
