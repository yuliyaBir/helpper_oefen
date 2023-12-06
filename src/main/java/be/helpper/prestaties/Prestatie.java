package be.helpper.prestaties;

import be.helpper.goedkeuringen.Goedkeuring;
import be.helpper.users.User;
import jakarta.persistence.*;

@Entity
@Table(name = "prestaties")
public class Prestatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String naam;
    private String omschrijving;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "assistent_id")
    private User assistent;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "budgethouder_id")
    private User budgethouder;

    public void setGoedkeuring(Goedkeuring goedkeuring) {
        this.goedkeuring = goedkeuring;
    }
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "goedkeuring_id")
    private Goedkeuring goedkeuring;
    public Prestatie(String naam, String omschrijving, User assistent, User budgethouder) {
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.assistent = assistent;
        this.budgethouder = budgethouder;
        this.goedkeuring = null;
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

    public Goedkeuring getGoedkeuring() {
        return goedkeuring;
    }
    public void setId(long id) {
        this.id = id;
    }
}
