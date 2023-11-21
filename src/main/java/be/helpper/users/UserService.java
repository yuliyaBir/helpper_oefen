package be.helpper.users;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Optional;

@ApplicationScoped

public class UserService {
    @Inject
    UserRepository userRepository;
//    @Inject
//    JsonWebToken jwt;
//    @Inject
//    public UserService(JsonWebToken jwt) {
//        this.jwt = jwt;
//    }
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public static boolean matches(User user, String wachtwoord) {
        return BcryptUtil.matches(wachtwoord, user.getWachtwoord());
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
//    public Optional<User> getCurrentUser() {
//        return findByEmail(jwt.getName());
//    }
}
