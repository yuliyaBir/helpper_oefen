package be.helpper.prestaties;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

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
    public List<Prestatie> lijstVanPrestatiesZonderGoedkeuring(){
        return em.createQuery("select p from Prestatie p where size(p.goedkeuring) = 0").getResultList();
    }
    public List<Prestatie> lijstVanPrestatiesMetGoedkeuring(){
        return em.createQuery("select p from Prestatie p where size(p.goedkeuring) > 0").getResultList();
    }
}
