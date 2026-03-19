package com.example.plataforma_agendamento.security;

import com.example.plataforma_agendamento.dto.LoginRequestDTO;
import com.example.plataforma_agendamento.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String autenticar(LoginRequestDTO dto){
        UsernamePasswordAuthenticationToken credenciais = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
        Authentication auth =  authenticationManager.authenticate(credenciais);
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        return jwtService.gerarToken(usuarioLogado);
    }


}
