package com.example.plataforma_agendamento.service;

import com.example.plataforma_agendamento.dto.AgendRequestDTO;
import com.example.plataforma_agendamento.dto.AgendResponseDTO;
import com.example.plataforma_agendamento.dto.UserResponseDTO;
import com.example.plataforma_agendamento.entity.Agendamento;
import com.example.plataforma_agendamento.entity.Usuario;
import com.example.plataforma_agendamento.exception.AgendamentoDuplicado;
import com.example.plataforma_agendamento.exception.UsuarioNaoEncontradoException;
import com.example.plataforma_agendamento.repository.AgendamentoRepository;
import com.example.plataforma_agendamento.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {


    private final AgendamentoRepository agendaRepository;
    private final UserRepository userRepository;

    public AgendamentoService(AgendamentoRepository agendaRepository, UserRepository userRepository) {
        this.agendaRepository = agendaRepository;
        this.userRepository = userRepository;
    }

    public AgendResponseDTO criarAgendamento(AgendRequestDTO dto, Usuario usuarioLogado){

        if (dto.getDataHora().isBefore(LocalDateTime.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nao é possivel realizar um agendamento no passado");
        } if (agendaRepository.existsByDataHora(dto.getDataHora())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este horário já está reservado.");
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setDescricao(dto.getDescricao());
        agendamento.setDataHora(dto.getDataHora());
        agendamento.setUser(usuarioLogado);

        Agendamento agendamentoSalvo = agendaRepository.save(agendamento);

        AgendResponseDTO agendResponseDTO = new AgendResponseDTO();
        agendResponseDTO.setId(agendamentoSalvo.getId());
        agendResponseDTO.setDataHora(agendamentoSalvo.getDataHora());
        agendResponseDTO.setDescricao(agendamentoSalvo.getDescricao());
        agendResponseDTO.setName(agendamentoSalvo.getUser().getName());

        return agendResponseDTO;
    }

    public Agendamento atualizarAgendamento(Long id, AgendRequestDTO dto){
        Agendamento agendamento = agendaRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario nao encontrado"));
        agendamento.setId(dto.getUserId());
        agendamento.setDataHora(dto.getDataHora());
        agendamento.setDescricao(dto.getDescricao());

        return agendaRepository.save(agendamento);
    }

    public List<AgendResponseDTO> buscarMeusAgendamentos(Usuario usuarioLogado){
        List<Agendamento> agendamentos = agendaRepository.findByUser(usuarioLogado);

        return agendamentos.stream().map(agendamento -> {
            AgendResponseDTO agendResponseDTO = new AgendResponseDTO();
            agendResponseDTO.setId(agendamento.getId());
            agendResponseDTO.setDescricao(agendamento.getDescricao());
            agendResponseDTO.setDataHora(agendamento.getDataHora());
            return agendResponseDTO;
        }).collect(Collectors.toList());

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