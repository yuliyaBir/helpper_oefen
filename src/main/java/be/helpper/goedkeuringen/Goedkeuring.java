package be.helpper.goedkeuringen;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@Table(name = "goedkeuringen")
//@Access(AccessType.FIELD)
public class Goedkeuring {
//    private long prestatieId;
    private LocalDate goedgekeurd;
    private String commentaar;
    private int uren;

//    public long getPrestatieId() {
//        return prestatieId;
//    }

    protected Goedkeuring() {
    }

    public Goedkeuring(LocalDate goedgekeurd, String commentaar, int uren) {
        this.goedgekeurd = goedgekeurd;
        this.commentaar = commentaar;
        this.uren = uren;
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
