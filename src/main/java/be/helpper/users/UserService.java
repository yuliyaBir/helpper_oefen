package be.helpper.users;

import be.helpper.exceptions.UserAlreadyExistsException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.Optional;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public User findByFamilienaam(String familienaam, String voornaam){
        return userRepository.findByFamilienaam(familienaam, voornaam).orElseThrow(NotFoundException::new);
    }

    public User createUser(User user){
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
//        return userRepository.createUser(user);
        if (byEmail.isEmpty()) {
            return userRepository.createUser(user);
        } else {
            throw new UserAlreadyExistsException();
        }
    }
    public void deleteUser(long id){
        userRepository.deleteUser(id);
    }
}
