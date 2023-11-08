package be.helpper.users;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository repo;
    public User findById(Long id){
        return repo.findById(id);
    }
    public User findByEmail(String email){
        return repo.findByEmail(email);
    }

    public static boolean matches(User user, String wachtwoord) {
        return BcryptUtil.matches(wachtwoord, user.getWachtwoord());
    }
    public User findByFamilienaam(String familienaam, String voornaam){
        return repo.findByFamilienaam(familienaam, voornaam).orElseThrow(NotFoundException::new);
    }
}
