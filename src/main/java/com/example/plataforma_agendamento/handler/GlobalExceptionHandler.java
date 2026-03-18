package com.example.plataforma_agendamento.handler;

import com.example.plataforma_agendamento.exception.AgendamentoDuplicado;
import com.example.plataforma_agendamento.exception.EmailJaCadastradoException;
import com.example.plataforma_agendamento.exception.UsuarioNaoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String>handleRuntimeException(RuntimeException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<String>emailJaCadastrado(EmailJaCadastradoException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<String>usuarioNaoEncontrado(UsuarioNaoEncontradoException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(AgendamentoDuplicado.class)
    public ResponseEntity<String>agendamentoDuplicado(AgendamentoDuplicado e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
