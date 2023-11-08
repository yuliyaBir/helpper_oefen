package be.helpper.users;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

@ApplicationScoped
public class UserRepository {
    @Inject
    EntityManager em;
    public void persist(User user){
        em.persist(user);
    }
    public User findById(Long id){
        return em.find(User.class, id);
    }
    public User findByEmail(String email){
        return em.find(User.class, email);
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
}