package be.helpper.users;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;
import java.util.Optional;

@ApplicationScoped

public class UserService {
    private final JsonWebToken jwt;
    @Inject
    public UserService(JsonWebToken jwt) {
        this.jwt = jwt;
    }
    @Inject
    UserRepository userRepository;
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public static boolean matches(String userWachtwoord, String wachtwoord) {
        return BcryptUtil.matches(wachtwoord, userWachtwoord);
    }
    public User findByFamilienaam(String familienaam, String voornaam){
        return userRepository.findByFamilienaam(familienaam, voornaam).orElseThrow(NotFoundException::new);
    }
    public User createUser(User user){
        return userRepository.createUser(user);
    }
    public void deleteUser(long id){
        userRepository.deleteUser(id);
    }
//    public User getCurrentUser() {
//        return findByEmail(jwt.getSubject()).orElseThrow(NotFoundException::new);
//    }
}
