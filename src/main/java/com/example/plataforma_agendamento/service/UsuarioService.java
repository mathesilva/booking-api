package com.example.plataforma_agendamento.service;

import com.example.plataforma_agendamento.dto.UserRequestDTO;
import com.example.plataforma_agendamento.dto.UserResponseDTO;
import com.example.plataforma_agendamento.entity.Usuario;
import com.example.plataforma_agendamento.exception.EmailJaCadastradoException;
import com.example.plataforma_agendamento.exception.UsuarioNaoEncontradoException;
import com.example.plataforma_agendamento.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario criarUsuario(UserRequestDTO dto){
        Optional<Usuario> usuarioExistente = userRepository.findByEmail(dto.getEmail());
        if (usuarioExistente.isPresent()){
            throw new EmailJaCadastradoException("Email ja existente!");
        }

        Usuario usuario = new Usuario();
        usuario.setName(dto.getName());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        return userRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, UserRequestDTO dto){
        Usuario usuario = userRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario nao cadastrado em nosso banco"));
        usuario.setName(dto.getName());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

    return userRepository.save(usuario);

    }

    public List<UserResponseDTO> listar(){
        List<Usuario> usuarios =  userRepository.findAll();

        return usuarios.stream().map(usuario -> {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setId(usuario.getId());
            dto.setEmail(usuario.getEmail());
            dto.setName(usuario.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    public UserResponseDTO buscarPorId(Long id){
        Usuario usuario = userRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario nao encontrado"));
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(usuario.getId());
        dto.setEmail(usuario.getEmail());
        dto.setName(usuario.getName());

        return dto;
    }

    public void deletarUsuario(Long id){
        if (!userRepository.existsById(id)){
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
        }
        userRepository.deleteById(id);
    }



}
