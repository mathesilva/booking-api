package com.example.plataforma_agendamento.service;

import com.example.plataforma_agendamento.Agendamento;
import com.example.plataforma_agendamento.Usuario;
import com.example.plataforma_agendamento.exception.AgendamentoDuplicado;
import com.example.plataforma_agendamento.exception.UsuarioNaoEncontradoException;
import com.example.plataforma_agendamento.repository.AgendamentoRepository;
import com.example.plataforma_agendamento.repository.UserRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {


    private final AgendamentoRepository agendaRepository;
    private final UserRepository userRepository;

    public AgendamentoService(AgendamentoRepository agendaRepository, UserRepository userRepository) {
        this.agendaRepository = agendaRepository;
        this.userRepository = userRepository;
    }

    public Agendamento criarAgendamento(Agendamento agendamento){
        //Buscar usuario
        Usuario usuario = userRepository.findById(agendamento.getUser().getId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario nao encontrado"));

        //Verficar conflito
        Optional<Agendamento>agendaExistente = agendaRepository.findByUserIdAndDataHora(usuario.getId(), agendamento.getDataHora());
        if (agendaExistente.isPresent()){
            throw new AgendamentoDuplicado("Ja existe um agendamento neste horario");
        }
        //associar usuario
        agendamento.setUser(usuario);
        //salvar usuario
        return agendaRepository.save(agendamento);
    }

    public List<Agendamento> listaAgendamentos(){
        return agendaRepository.findAll();
    }

    public Agendamento buscarAgendaPorID(Long id){
        return agendaRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Agendamento nao encontrado em nosso banco"));
    }

    public void deletarAgendamento(Long id){
        if (!agendaRepository.existsById(id)){
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
        }
        agendaRepository.deleteById(id);
    }






}