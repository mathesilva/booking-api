package com.example.plataforma_agendamento.repository;

import com.example.plataforma_agendamento.entity.Agendamento;
import com.example.plataforma_agendamento.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    Optional<Agendamento> findByUserIdAndDataHora(Long id, LocalDateTime dataHora);
    List<Agendamento> findByUser(Usuario usuario);
    boolean existsByDataHora(LocalDateTime dataHora);
}

