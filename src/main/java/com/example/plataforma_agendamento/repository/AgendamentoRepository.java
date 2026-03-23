package com.example.plataforma_agendamento.repository;

import com.example.plataforma_agendamento.entity.Agendamento;
import com.example.plataforma_agendamento.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    Optional<Agendamento> findByUserIdAndDataHora(Long id, LocalDateTime dataHora);
    Page<Agendamento> findByUser(Usuario usuario, Pageable pageable);
    boolean existsByDataHora(LocalDateTime dataHora);
}

