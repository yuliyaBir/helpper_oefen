package be.helpper.users;


import be.helpper.rollen.Rol;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "users")
@UserDefinition
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable=false, updatable=false)
    private Long id;
    @NotBlank
    private String voornaam;
    @NotBlank
    private String familienaam;
    @NotBlank
    @Username
    private String email;
    @NotNull
    @Column(nullable = false)
    @Password
    private String wachtwoord;
    @Roles
    @Column(name = "rollen")
    private String rol;
    public User(String voornaam, String familienaam, String email, String wachtwoord, String rol) {
        this.id = Long.valueOf(0);
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
        this.rol = rol;
        this.wachtwoord = BcryptUtil.bcryptHash(wachtwoord);
    }
    protected User() {
    }

    //    public void add(Rol rol){
//        if (!rollen.add(rol)){
//           throw new UserHeeftDezeRolAlException();
//        }
//    }
//    public Set<Rol> getRollen(){
//        return Collections.unmodifiableSet(rollen);
//    }
    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getEmail() {
        return email;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }
    public String getRol() {
        return rol;
    }
//    @JsonProperty("password")
    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
