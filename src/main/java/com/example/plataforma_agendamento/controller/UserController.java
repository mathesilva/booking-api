package com.example.plataforma_agendamento.controller;

import com.example.plataforma_agendamento.dto.UserRequestDTO;
import com.example.plataforma_agendamento.dto.UserResponseDTO;
import com.example.plataforma_agendamento.entity.Usuario;
import com.example.plataforma_agendamento.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UsuarioService userService;

    public UserController(UsuarioService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> criarUsuario(@RequestBody UserRequestDTO dto) {

        Usuario usuario = userService.criarUsuario(dto);

        UserResponseDTO response = new UserResponseDTO();
        response.setId(usuario.getId());
        response.setName(usuario.getName());
        response.setEmail(usuario.getEmail());


        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody UserRequestDTO dto){
            userService.atualizarUsuario(id, dto);
            Usuario usuario = userService.atualizarUsuario(id,dto);
            return ResponseEntity.ok(usuario);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = userService.listar();
        return ResponseEntity.ok(usuarios);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorID(@PathVariable Long id) {
            Usuario usuario = userService.buscarPorId(id);
            return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
            userService.deletarUsuario(id);
            return ResponseEntity.noContent().build();
    }

}
