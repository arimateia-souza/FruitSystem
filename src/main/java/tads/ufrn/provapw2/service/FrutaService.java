package tads.ufrn.provapw2.service;

import tads.ufrn.provapw2.model.Fruta;
import tads.ufrn.provapw2.repository.FrutaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FrutaService {
    private FrutaRepository repository;

    public FrutaService(FrutaRepository frutaRepository) {

        this.repository = frutaRepository;
    }

    public void salvarFruta(Fruta fruta) {
        repository.save(fruta);
    }

    public List<Fruta> listarFrutas() {
        return repository.findByDeletedIsNull();
    }

    public Optional<Fruta> buscarFrutaPorId(Long id) {

        return repository.findById(id);
    }

    public void deletarFruta(Long id) {
        Fruta fruta = repository.findByIdAndDeletedIsNull(id);
        if(fruta != null){
            fruta.setDeleted(LocalDate.now());
            repository.save(fruta);
        }

    }
    public void atualizarEstoque(Long id, int novaQuantidade) {
        Optional<Fruta> optionalFruta = repository.findById(id);
        if (optionalFruta.isPresent()) {
            Fruta fruta = optionalFruta.get();
            fruta.setQtdEstoque(novaQuantidade);
            repository.save(fruta);
        } else {
            throw new FrutaNotFoundException("Fruta não encontrada");
        }
    }
}
