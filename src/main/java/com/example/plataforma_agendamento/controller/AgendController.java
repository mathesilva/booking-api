package com.example.plataforma_agendamento.controller;

import com.example.plataforma_agendamento.Agendamento;
import com.example.plataforma_agendamento.service.AgendamentoService;
import com.example.plataforma_agendamento.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendController {

    private final AgendamentoService agendamentoService;


    public AgendController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public Agendamento CriarAgendamento(@RequestBody Agendamento agendamento){
        return agendamentoService.criarAgendamento(agendamento);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Agendamento>> listarAgendamentos(){
        List<Agendamento> agendamentoList = agendamentoService.listaAgendamentos();
        return ResponseEntity.ok(agendamentoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento>buscarAgendaPorID(@PathVariable Long id){
        try{
            Agendamento agendamento = agendamentoService.buscarAgendaPorID(id);
            return ResponseEntity.ok(agendamento);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id){
        try{
            agendamentoService.deletarAgendamento(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }








    }

