package com.example.plataforma_agendamento.controller;

import com.example.plataforma_agendamento.Usuario;
import com.example.plataforma_agendamento.service.UsuarioService;
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
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return userService.criarUsuario(usuario);
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
