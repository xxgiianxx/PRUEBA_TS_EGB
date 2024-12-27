package org.examen.pruebateamsoftegb.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table("participante")
@Data
public class Participante  implements Serializable {

    private static final long serialVersionUID = -2902454995673756813L;

    @Id
    private Long participanteid;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String direccion;

    private String telefono;

}

