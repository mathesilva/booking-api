package com.example.plataforma_agendamento.controller;

import com.example.plataforma_agendamento.dto.AgendRequestDTO;
import com.example.plataforma_agendamento.dto.AgendResponseDTO;
import com.example.plataforma_agendamento.entity.Agendamento;
import com.example.plataforma_agendamento.service.AgendamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AgendResponseDTO> CriarAgendamento(@RequestBody AgendRequestDTO dto){

        Agendamento agendamento = agendamentoService.criarAgendamento(dto);
        AgendResponseDTO responseDTO = new AgendResponseDTO();
        responseDTO.setUserId(agendamento.getId());
        responseDTO.setDataHora(agendamento.getDataHora());
        responseDTO.setDescricao(agendamento.getDescricao());
        responseDTO.setId(agendamento.getUser().getId());
        responseDTO.setName(agendamento.getUser().getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable Long id, @RequestBody AgendRequestDTO dto){
           Agendamento agendamento = agendamentoService.atualizarAgendamento(id, dto);
           return ResponseEntity.ok(agendamento);

    }

    @GetMapping("/lista")
    public ResponseEntity<List<Agendamento>> listarAgendamentos(){
        List<Agendamento> agendamentoList = agendamentoService.listaAgendamentos();
        return ResponseEntity.ok(agendamentoList);
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

