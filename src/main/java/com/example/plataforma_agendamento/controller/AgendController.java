package com.example.plataforma_agendamento.controller;

import com.example.plataforma_agendamento.dto.AgendRequestDTO;
import com.example.plataforma_agendamento.dto.AgendResponseDTO;
import com.example.plataforma_agendamento.entity.Agendamento;
import com.example.plataforma_agendamento.entity.Usuario;
import com.example.plataforma_agendamento.service.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendController {

    private final AgendamentoService agendamentoService;


    public AgendController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public ResponseEntity<AgendResponseDTO> CriarAgendamento(@RequestBody AgendRequestDTO dto, @AuthenticationPrincipal Usuario usuarioLogado){
        AgendResponseDTO response = agendamentoService.criarAgendamento(dto, usuarioLogado);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable Long id, @RequestBody AgendRequestDTO dto){
           Agendamento agendamento = agendamentoService.atualizarAgendamento(id, dto);
           return ResponseEntity.ok(agendamento);

    }

    @GetMapping("/me")
    public ResponseEntity<List<AgendResponseDTO>> listarMeusAgendamentos(@AuthenticationPrincipal Usuario usuarioLogado){
        List<AgendResponseDTO> meusAgendamentos = agendamentoService.buscarMeusAgendamentos(usuarioLogado);
        return ResponseEntity.ok(meusAgendamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento>buscarAgendaPorID(@PathVariable Long id){
            Agendamento agendamento = agendamentoService.buscarAgendaPorID(id);
            return ResponseEntity.ok(agendamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id){
            agendamentoService.deletarAgendamento(id);
            return ResponseEntity.noContent().build();
    }








    }

