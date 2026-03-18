package com.example.plataforma_agendamento;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class Usuario{

    @OneToMany(mappedBy = "user")
    private List<Agendamento> agendamentos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;

    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
