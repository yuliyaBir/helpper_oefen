package be.helpper.users;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.decorator.Decorator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

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
}