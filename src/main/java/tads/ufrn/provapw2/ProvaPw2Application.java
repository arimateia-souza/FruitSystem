package tads.ufrn.provapw2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import tads.ufrn.provapw2.model.Usuario;
import tads.ufrn.provapw2.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ProvaPw2Application {

    @Bean
    CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        return args -> {

            List<Usuario> users = Stream.of(
                    new Usuario(1L, "user", "323.456.789-10", "admin", encoder.encode("admin"), true),
                    new Usuario(2L, "Nivea", "472.456.789-10", "user1", encoder.encode("user1"), true),
                    new Usuario(3L, "Arimateia", "845.456.789-10", "user2", encoder.encode("user2"), false)//não é admin, não pode alterar no produto
            ).collect(Collectors.toList());

            for (var e : users) {
                System.out.println(e);
            }
            usuarioRepository.saveAll(users);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ProvaPw2Application.class, args);
    }

}
