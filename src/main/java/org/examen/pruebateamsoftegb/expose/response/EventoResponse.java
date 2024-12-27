package org.examen.pruebateamsoftegb.expose.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventoResponse {
    private Long eventoid;
    private String nombre;
}
