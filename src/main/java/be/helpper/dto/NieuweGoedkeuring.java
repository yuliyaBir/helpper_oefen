package be.helpper.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NieuweGoedkeuring(@NotBlank String commentaar, @NotNull int uren) {
}
