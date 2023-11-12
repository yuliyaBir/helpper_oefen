package be.helpper.goedkeuringen;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "goedkeuringen")
//@Access(AccessType.FIELD)
public class Goedkeuring {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @NotNull
    private LocalDate goedgekeurd;
    @NotBlank
    private String commentaar;
    @NotNull
    private int uren;
//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "prestatie_id")
//    @JsonIgnore
//    private Prestatie prestatie;
    public Goedkeuring() {
    }

    public Goedkeuring(LocalDate goedgekeurd, String commentaar, int uren) {
        this.id = 0;
        this.goedgekeurd = goedgekeurd;
        this.commentaar = commentaar;
        this.uren = uren;
    }
    public long getId() {
        return id;
    }
//    public Prestatie getPrestatie() {
//        return prestatie;
//    }
public void setId(long id) {
    this.id = id;
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
