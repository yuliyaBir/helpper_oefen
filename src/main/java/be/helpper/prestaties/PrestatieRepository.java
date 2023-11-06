package be.helpper.prestaties;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class PrestatieRepository{
    @Inject
    EntityManager em;
    public long createPrestatie(Prestatie prestatie){
        em.persist(prestatie);
        return prestatie.getId();
    }
    public Prestatie findPrestatieById(long id){
        return em.find(Prestatie.class, id);
    }
}
