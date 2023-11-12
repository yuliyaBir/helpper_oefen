package be.helpper.users;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name = "users")
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
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String wachtwoord;
//    @ManyToMany(mappedBy = "users")
//    private Set<Rol> rollen;
//    @Enumerated(EnumType.STRING)
//    private Rol rol;
    public User(String voornaam, String familienaam, String email, String wachtwoord) {
        this.id = Long.valueOf(0);
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
//        this.rol = rol;
        this.wachtwoord = wachtwoord;
    }
    public User() {
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
    //    public Rol getRol() {
//        return rol;
//    }

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
