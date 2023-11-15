package be.helpper.prestaties;

import be.helpper.goedkeuringen.Goedkeuring;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PrestatieRepository{
    @Inject
    EntityManager em;
    public void createPrestatie(Prestatie prestatie){
        em.persist(prestatie);
    }
    public Optional<Prestatie> findPrestatieById(long id){
        return Optional.ofNullable(em.find(Prestatie.class, id));
    }
    public List<Prestatie> lijstVanPrestatiesZonderGoedkeuring(){
        return em.createQuery("select p from Prestatie p where p.goedkeuring = null").getResultList();
    }
    public List<Prestatie> lijstVanPrestatiesMetGoedkeuring(){
        return em.createQuery("select p from Prestatie p where p.goedkeuring != null").getResultList();
    }
    @Transactional
    public void updatePrestatie(long prestatieId, Goedkeuring goedkeuring){
        var prestatie = em.find(Prestatie.class, prestatieId, LockModeType.PESSIMISTIC_WRITE);
        prestatie.setGoedkeuring(goedkeuring);
        em.persist(prestatie);
    }
    @Transactional
    public void deletePrestatie(long id){
        var prestatie = em.find(Prestatie.class, id, LockModeType.PESSIMISTIC_WRITE);
        em.remove(prestatie);
    }
}
