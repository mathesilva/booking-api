package com.example.plataforma_agendamento.service;

import com.example.plataforma_agendamento.Usuario;
import com.example.plataforma_agendamento.exception.EmailJaCadastradoException;
import com.example.plataforma_agendamento.exception.UsuarioNaoEncontradoException;
import com.example.plataforma_agendamento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService{

    @Autowired
    private final UserRepository userRepository;

    public UsuarioService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Usuario criarUsuario(Usuario usuario){
        Optional<Usuario> usuarioExistente = userRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent()){
            throw new EmailJaCadastradoException("Email ja existente!");
        }
        return userRepository.save(usuario);
    }

    public List<Usuario> listar(){
        return userRepository.findAll();
    }

    public Usuario buscarPorId(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario nao encontrado"));
    }

    public void deletarUsuario(Long id){
        if (!userRepository.existsById(id)){
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
        }
        userRepository.deleteById(id);
    }



}
