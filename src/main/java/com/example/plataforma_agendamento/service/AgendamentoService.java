package com.example.plataforma_agendamento.service;

import com.example.plataforma_agendamento.dto.AgendRequestDTO;
import com.example.plataforma_agendamento.entity.Agendamento;
import com.example.plataforma_agendamento.entity.Usuario;
import com.example.plataforma_agendamento.exception.AgendamentoDuplicado;
import com.example.plataforma_agendamento.exception.UsuarioNaoEncontradoException;
import com.example.plataforma_agendamento.repository.AgendamentoRepository;
import com.example.plataforma_agendamento.repository.UserRepository;
import org.springframework.stereotype.Service;

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

    public Agendamento criarAgendamento(AgendRequestDTO dto){
        //Buscar usuario
        Usuario usuario = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario nao encontrado"));

        //Verficar conflito
        Optional<Agendamento>agendaExistente = agendaRepository.findByUserIdAndDataHora(dto.getUserId(), dto.getDataHora());
        if (agendaExistente.isPresent()){
            throw new AgendamentoDuplicado("Ja existe um agendamento neste horario");
        }
        //associar usuario
        Agendamento agendamento = new Agendamento();
        agendamento.setDescricao(dto.getDescricao());
        agendamento.setDataHora(dto.getDataHora());
        agendamento.setUser(usuario);
        //salvar usuario
        return agendaRepository.save(agendamento);
    }

    public Agendamento atualizarAgendamento(Long id, AgendRequestDTO dto){
        Agendamento agendamento = agendaRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario nao encontrado"));
        agendamento.setId(dto.getUserId());
        agendamento.setDataHora(dto.getDataHora());
        agendamento.setDescricao(dto.getDescricao());

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