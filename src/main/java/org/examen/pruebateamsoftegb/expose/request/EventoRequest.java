package org.examen.pruebateamsoftegb.expose.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;


@Data
public class EventoRequest implements Serializable {

    private static final long serialVersionUID = 3899732232639534093L;

    private Long eventoId;

    @NotNull(message = "El nombre no debe ser nulo")
    private String nombre;

    @NotNull(message = "El tipo de evento no debe ser nulo")
    private String tipoevento;

    @NotNull(message = "El ID del participante no debe ser nulo")
    private Long participanteid;

    @NotNull(message = "La fecha del evento no debe ser nula")
    private LocalDate fechaevento;

    @NotNull(message = "El lugar no debe ser nulo")
    private String lugar;


}
