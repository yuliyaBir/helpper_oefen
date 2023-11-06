package be.helpper.prestaties;

import be.helpper.goedkeuringen.Goedkeuring;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "prestaties")
public class Prestatie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private String omschrijving;
    private long assistentId;
    private long budgethouderId;
    @ElementCollection
    @CollectionTable(name = "goedkeuringen", joinColumns =
    @JoinColumn(name = "prestatie_id"))
    private Set<Goedkeuring> goedkeuring;
    public Prestatie(long id, String naam, String omschrijving, long assistentId, long budgethouderId) {
        this.id = id;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.assistentId = assistentId;
        this.budgethouderId = budgethouderId;
        this.goedkeuring = new LinkedHashSet<>(

        );
    }

    public Prestatie(String naam, String omschrijving, long assistentId, long budgethouderId) {
        this.id = 0;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.assistentId = assistentId;
        this.budgethouderId = budgethouderId;
        this.goedkeuring = new LinkedHashSet<>();
    }

    public Prestatie() {
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public long getAssistentId() {
        return assistentId;
    }

    public long getBudgethouderId() {
        return budgethouderId;
    }
    public Set<Goedkeuring> getGoedkeuring() {
        return Collections.unmodifiableSet(goedkeuring);
    }
}
