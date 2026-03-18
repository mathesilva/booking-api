package com.example.plataforma_agendamento.repository;

import com.example.plataforma_agendamento.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    Optional<Agendamento> findByUserIdAndDataHora(Long id, LocalDateTime dataHora);

}

