package be.helpper.goedkeuringen;

import be.helpper.prestaties.Prestatie;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "goedkeuringen")
public class Goedkeuring {
    @Id
    @Column(name = "prestatie_id")
    private Long prestatieId;
    private LocalDate goedgekeurd;
    private String commentaar;
    private int uren;

    public Prestatie getPrestatie() {
        return prestatie;
    }

    public void setPrestatie(Prestatie prestatie) {
        this.prestatie = prestatie;
    }

    @OneToOne(mappedBy = "goedkeuring")
    private Prestatie prestatie;

    protected Goedkeuring() {
    }
    public long getPrestatieId() {
        return prestatieId;
    }
    public Goedkeuring(long id, LocalDate goedgekeurd, String commentaar, int uren) {
        this.goedgekeurd = goedgekeurd;
        this.commentaar = commentaar;
        this.uren = uren;
        this.prestatieId = id;
    }

    public LocalDate getGoedgekeurd() {
        return goedgekeurd;
    }

    public String getCommentaar() {
        return commentaar;
    }

    public int getUren() {
        return uren;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Goedkeuring that)) return false;
        return uren == that.uren && Objects.equals(goedgekeurd, that.goedgekeurd) && Objects.equals(commentaar, that.commentaar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goedgekeurd, commentaar, uren);
    }
}
