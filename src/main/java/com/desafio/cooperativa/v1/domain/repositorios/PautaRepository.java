package com.desafio.cooperativa.v1.domain.repositorios;

import com.desafio.cooperativa.v1.domain.entidades.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PautaRepository extends JpaRepository<Pauta, UUID> {
}
