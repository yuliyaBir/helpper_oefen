package be.helpper.users;

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
    @NotBlank
    private String rol;
    public User(String voornaam, String familienaam, String email, String wachtwoord, String rol) {
        this.id = 0L;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
        if (rol.equals("assistent") || rol.equals("budgethouder")){
            this.rol = rol;
        } else {
            throw new RuntimeException("Rol is niet correct ingevuld");
        }
        this.wachtwoord = BcryptUtil.bcryptHash(wachtwoord);
    }
    protected User() {
    }
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
    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = BcryptUtil.bcryptHash(wachtwoord);
    }

    public void setRol(String rol) {
        if (rol.equals("assistent") || rol.equals("budgethouder")){
            this.rol = rol;
        } else {
            throw new RuntimeException("Rol is niet correct ingevuld");
        }
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
