package tads.ufrn.provapw2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tads.ufrn.provapw2.model.Fruta;

import java.util.List;

public interface FrutaRepository extends JpaRepository<Fruta, Long> {
    List<Fruta> findByDeletedIsNull(); // retornar apenas as frutas que não foram marcadas como excluídas logicamente
    Fruta findByIdAndDeletedIsNull(Long id); //  busca uma fruta específica pelo id, mas apenas se ela não estiver marcada como excluída logicamente.
    //List<Fruta> findById(Long id);
    //List<Fruta> findFrutaByCategoria(String categoria);
}
