package be.helpper.goedkeuringen;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class GoedkeuringRepository {
    @Inject
    EntityManager em;
    public Goedkeuring createGoedkeuring(Goedkeuring goedkeuring){
        em.persist(goedkeuring);
        em.flush();
        return em.find(Goedkeuring.class, goedkeuring.getId());
    }
    public void deleteGoedkeuring(Goedkeuring goedkeuring){
        em.remove(goedkeuring);
    }
}
