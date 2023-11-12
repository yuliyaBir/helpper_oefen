package be.helpper.users;

import be.helpper.goedkeuringen.Goedkeuring;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class UserRepository {
    @Inject
    EntityManager em;

    public void persist(User user) {
        em.persist(user);
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(em.find(User.class, email));
    }

    //    public User findByFamilienaam(String familienaam){
//        return em.find(User.class,familienaam);
//    }
    public Optional<User> findByFamilienaam(String familienaam, String voornaam) {
        TypedQuery<User> query = em.createQuery("SELECT usr FROM User usr WHERE usr.familienaam = :familienaam and usr.voornaam = :voornaam", User.class);
        query.setParameter("familienaam", familienaam);
        query.setParameter("voornaam", voornaam);
        return query.getResultStream().findFirst();
    }

    @Transactional
    public User createUser(User user) {
        em.persist(user);
        em.flush();
        return em.find(User.class, user.getId());
    }
    @Transactional
    public void deleteUser(long id){
        var user = em.find(User.class, id);
        em.remove(user);
    }
    public void performWorkGeneratingError(){
        em.find(User.class, Long.MAX_VALUE);
    }

//    public Optional<User> findByIdOptional(long id){return findByIdOptional(id);}
}