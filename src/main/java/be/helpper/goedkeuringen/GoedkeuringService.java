package be.helpper.goedkeuringen;

import be.helpper.prestaties.Prestatie;
import be.helpper.prestaties.PrestatieRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class GoedkeuringService {
    @Inject
    GoedkeuringRepository goedkeuringRepository;
    @Inject
    PrestatieRepository prestatieRepository;
    @Transactional
    public void createGoedkeuringVoorPrestatie(long prestatieId, Goedkeuring goedkeuring){
        var goedkeuringMetId = goedkeuringRepository.createGoedkeuring(goedkeuring);
        prestatieRepository.updatePrestatie(prestatieId, goedkeuringMetId);
    }
    public Optional<Prestatie> findGoedkeuringById(long id){
        return prestatieRepository.findPrestatieById(id);
    }
}
