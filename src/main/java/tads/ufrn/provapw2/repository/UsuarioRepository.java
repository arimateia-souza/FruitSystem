package tads.ufrn.provapw2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tads.ufrn.provapw2.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findUsuarioByLogin(String login);


}
