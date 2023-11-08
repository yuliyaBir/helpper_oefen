package be.helpper.auth;

import be.helpper.users.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.security.AuthenticationFailedException;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.
        ConfigProperty;

import java.time.Duration;
import java.util.HashSet;
@ApplicationScoped
public class AuthService {
    private final String issuer;
    private final UserService userService;
    @Inject
    public AuthService(
            @ConfigProperty(name = "mp.jwt.verify.issuer") String issuer, UserService userService){
        this.issuer = issuer;
        this.userService = userService;
    }
//    public Uni<String> authenticate(AuthRequest authRequest){
//        return userService.findByEmail(authRequest.email()
//                .transform(user -> {
//                    if (user == null || !UserService.matches(user.getWachtwoord(),
//                            authRequest.wachtwoord())) {
//                        throw new AuthenticationFailedException
//                                ("Invalid credentials");
//                    }
//                    return Jwt.issuer(issuer)
//                            .upn(user.getEmail())
//                            .groups(new HashSet<>(user.roles))
//                            .expiresIn(Duration.ofHours(1L))
//                            .sign();
//                });
//    }
}
