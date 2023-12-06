package be.helpper.goedkeuringen;

import be.helpper.prestaties.PrestatieRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class GoedkeuringService {
    @Inject
    GoedkeuringRepository goedkeuringRepository;
    @Inject
    PrestatieRepository prestatieRepository;
    @Transactional
    public long createGoedkeuringForPrestatie(long prestatieId, Goedkeuring goedkeuring){
        var goedkeuringWithId = goedkeuringRepository.createGoedkeuring(goedkeuring);
        prestatieRepository.updatePrestatie(prestatieId, goedkeuringWithId);
        return goedkeuringWithId.getId();
    }
}
