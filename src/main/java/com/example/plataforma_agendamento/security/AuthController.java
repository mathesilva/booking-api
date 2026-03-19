package com.example.plataforma_agendamento.security;

import com.example.plataforma_agendamento.dto.LoginRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody LoginRequestDTO dto){
        try {
            String token = authService.autenticar(dto);
            return ResponseEntity.ok(token);
        }catch (Exception e){
            return ResponseEntity.status(401).body("Credenciais inválidas! Verifique seu email e senha.");
        }
    }
}
