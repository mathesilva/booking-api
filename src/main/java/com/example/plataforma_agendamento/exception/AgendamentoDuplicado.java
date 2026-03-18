package com.example.plataforma_agendamento.exception;

public class AgendamentoDuplicado extends RuntimeException {
    public AgendamentoDuplicado(String message) {
        super(message);
    }
}
