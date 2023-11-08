package be.helpper.rollen;

import be.helpper.users.User;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rollen")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
//    @ManyToMany
//    @JoinTable(
//            name = "rollenPerUser",
//            joinColumns = @JoinColumn(name = "rol_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private Set<User> users;
//    public void add(User user) {
//        if (!users.add(user)) {
//            throw new UserHeeftDezeRolAlException();
//        }
//    }
//    public Set<User> getUsers() {
//        return Collections.unmodifiableSet(users);
//    }
    public long getId() {
        return id;
    }
    public String getNaam() {
        return naam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;
        return id == role.id && Objects.equals(naam, role.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naam);
    }
}
