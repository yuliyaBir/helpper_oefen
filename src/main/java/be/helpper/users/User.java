package be.helpper.users;


import be.helpper.rollen.Rol;
import be.helpper.rollen.UserHeeftDezeRolAlException;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String voornaam;
    private String familienaam;
    private String email;
    @ManyToMany(mappedBy = "users")
    private Set<Rol> rollen;

    public User() {
    }

    public User(String voornaam, String familienaam, String email) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
        this.rollen = new LinkedHashSet<>();
    }

    public void add(Rol rol){
        if (!rollen.add(rol)){
           throw new UserHeeftDezeRolAlException();
        }
    }
    public Set<Rol> getRollen(){
        return Collections.unmodifiableSet(rollen);
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
