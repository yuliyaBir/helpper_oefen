package be.helpper.goedkeuringen;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class GoedkeuringService {
    @Inject
    GoedkeuringRepository goedkeuringRepository;
    @Transactional
    public void createGoedkeuring(Goedkeuring goedkeuring){
       goedkeuringRepository.createGoedkeuring(goedkeuring);
    }
}
