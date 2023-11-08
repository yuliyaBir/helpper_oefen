package be.helpper.prestaties;

import be.helpper.goedkeuringen.Goedkeuring;
import be.helpper.users.User;
import jakarta.persistence.*;

import java.util.Collections;
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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "assistent_id")
    private User assistent;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "budgethouder_id")
    private User budgethouder;
    @ElementCollection
    @CollectionTable(name = "goedkeuringen", joinColumns =
    @JoinColumn(name = "prestatie_id"))
    private Set<Goedkeuring> goedkeuring;
    public Prestatie(long id, String naam, String omschrijving, User assistent, User budgethouder) {
        this.id = id;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.assistent = assistent;
        this.budgethouder = budgethouder;
        this.assistent = assistent;
        this.budgethouder = budgethouder;
        this.goedkeuring = new LinkedHashSet<>();
    }

    public Prestatie(String naam, String omschrijving, User assistent, User budgethouder) {
        this.id = 0;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.assistent = assistent;
        this.budgethouder = budgethouder;
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

    public User getAssistent() {
        return assistent;
    }

    public User getBudgethouder() {
        return budgethouder;
    }
    public Set<Goedkeuring> getGoedkeuring() {
        return Collections.unmodifiableSet(goedkeuring);
    }
}
