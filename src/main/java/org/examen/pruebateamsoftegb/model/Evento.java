package org.examen.pruebateamsoftegb.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Table("evento")
@Data
@Builder
public class Evento {

    @Id
    private Long eventoid;
    private String nombre;
    private String tipoevento;
    private Long participanteid;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaevento;
    private String lugar;


}
