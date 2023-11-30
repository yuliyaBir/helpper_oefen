package be.helpper.users;

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

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    public Optional<User> findByEmail(String email) {
        var query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        return query.getResultStream().findFirst();
    }

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
        return findById(user.getId()).get();
    }

    @Transactional
    public void deleteUser(long id) {
        var user = em.find(User.class, id);
        em.remove(user);
    }
}