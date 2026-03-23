package com.example.plataforma_agendamento;

import com.example.plataforma_agendamento.entity.Usuario;
import com.example.plataforma_agendamento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args)throws Exception {
        String emailAdmin = "admin@plataforma.com";

        if (userRepository.findByEmail(emailAdmin).isEmpty()) {
            Usuario admin = new Usuario();
            admin.setName("Adminstrador");
            admin.setEmail(emailAdmin);
            admin.setSenha(passwordEncoder.encode("Admin123"));
            admin.setRole("ROLE_ADMIN");

            userRepository.save(admin);

            System.out.println("Conta adm criada com sucesso!");
        } else {
            System.out.println("Adm ja existe");
        }
    }
}
