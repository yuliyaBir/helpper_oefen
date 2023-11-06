package be.helpper.users;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository repo;
    public User findById(Long id){
        return repo.findById(id);
    }
}
