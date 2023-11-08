package be.helpper.goedkeuringen;

import be.helpper.prestaties.Prestatie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class GoedkeuringRepository {
    @Inject
    EntityManager em;
    @Transactional
    public void createGoedkeuring(Goedkeuring goedkeuring){
        em.persist(goedkeuring);
    }
}
