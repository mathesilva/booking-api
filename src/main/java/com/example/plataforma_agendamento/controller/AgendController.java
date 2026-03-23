package com.example.plataforma_agendamento.controller;

import com.example.plataforma_agendamento.dto.AgendRequestDTO;
import com.example.plataforma_agendamento.dto.AgendResponseDTO;
import com.example.plataforma_agendamento.entity.Agendamento;
import com.example.plataforma_agendamento.entity.Usuario;
import com.example.plataforma_agendamento.service.AgendamentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Page<AgendResponseDTO>> listarMeusAgendamentos(@AuthenticationPrincipal Usuario usuarioLogado, @PageableDefault(size = 10, sort = "dataHora") Pageable pageable){
        Page<AgendResponseDTO> pagina = agendamentoService.buscarMeusAgendamentos(usuarioLogado, pageable);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<AgendResponseDTO>>listarTodosAgendamentos(@PageableDefault(size = 10, sort = "dataHora") Pageable pageable){
        Page<AgendResponseDTO> pagina = agendamentoService.buscarTodos(pageable);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento>buscarAgendaPorID(@PathVariable Long id){
            Agendamento agendamento = agendamentoService.buscarAgendaPorID(id);
            return ResponseEntity.ok(agendamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado){
            agendamentoService.deletarAgendamento(id, usuarioLogado);
            return ResponseEntity.noContent().build();
    }








    }

