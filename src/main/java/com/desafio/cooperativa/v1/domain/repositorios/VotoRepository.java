package com.desafio.cooperativa.v1.domain.repositorios;

import com.desafio.cooperativa.v1.domain.entidades.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VotoRepository extends JpaRepository<Voto, UUID> {
}
