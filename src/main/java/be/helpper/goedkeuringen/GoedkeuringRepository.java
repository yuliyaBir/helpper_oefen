package be.helpper.goedkeuringen;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class GoedkeuringRepository {
    @Inject
    EntityManager em;
    @Transactional
    public Goedkeuring createGoedkeuring(Goedkeuring goedkeuring){
        em.persist(goedkeuring);
        em.flush();
        return em.find(Goedkeuring.class, goedkeuring.getId());
    }
    public Optional<Goedkeuring> findGoedkeuringById(long id){
        return Optional.ofNullable(em.find(Goedkeuring.class, id));
    }
}
