package com.desafio.cooperativa.v1.domain.repositorios;

import com.desafio.cooperativa.v1.domain.entidades.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssociadoRepository extends JpaRepository<Associado, UUID> {
}
