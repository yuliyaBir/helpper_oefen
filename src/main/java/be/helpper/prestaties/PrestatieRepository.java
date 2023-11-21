package be.helpper.prestaties;

import be.helpper.goedkeuringen.Goedkeuring;
import be.helpper.goedkeuringen.GoedkeuringIsAlToegevoegdException;
import be.helpper.users.User;
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
    public long createPrestatie(Prestatie prestatie){
        em.persist(prestatie);
        em.flush();
        return prestatie.getId();
    }
    public Optional<Prestatie> findPrestatieById(long id){
        return Optional.ofNullable(em.find(Prestatie.class, id));
    }
    public List<Prestatie> lijstVanPrestatiesZonderGoedkeuringVoorBepaaldeAssistent(long assistentId){
        var query = em.createQuery("SELECT p FROM Prestatie p WHERE p.goedkeuring = null and p.assistent.id  = :assistentId", Prestatie.class);
        query.setParameter("assistentId", assistentId);
        return query.getResultList();
    }
    public List<Prestatie> lijstVanPrestatiesZonderGoedkeuringVoorBepaaldeBudgethouder(long budgethouderId){
        var query = em.createQuery("SELECT p FROM Prestatie p WHERE p.goedkeuring = null and p.budgethouder.id = :budgethouderId", Prestatie.class);
        query.setParameter("budgethouderId", budgethouderId);
        return query.getResultList();
    }

    public List<Prestatie> lijstVanPrestatiesMetGoedkeuringVoorBepaaldeBudgethouder(long budgethouderId){
        var query = em.createQuery("SELECT p FROM Prestatie p WHERE p.goedkeuring != null and p.budgethouder.id = :budgethouderId", Prestatie.class);
        query.setParameter("budgethouderId", budgethouderId);
        return query.getResultList();
    }
    public List<Prestatie> lijstVanPrestatiesMetGoedkeuringVoorBepaaldeAssistent(long assistentId){
        var query = em.createQuery("SELECT p FROM Prestatie p WHERE p.goedkeuring != null and p.assistent.id  = :assistentId", Prestatie.class);
        query.setParameter("assistentId", assistentId);
        return query.getResultList();
    }
    public void updatePrestatie(long prestatieId, Goedkeuring goedkeuring){
        var prestatie = em.find(Prestatie.class, prestatieId, LockModeType.PESSIMISTIC_WRITE);
        if (prestatie.getGoedkeuring() == null){
            prestatie.setGoedkeuring(goedkeuring);
            em.persist(prestatie);
        } else{
            throw new GoedkeuringIsAlToegevoegdException();
        }

    }
    public void deletePrestatie(long id){
        var prestatie = em.find(Prestatie.class, id, LockModeType.PESSIMISTIC_WRITE);
        em.remove(prestatie);
    }
}
