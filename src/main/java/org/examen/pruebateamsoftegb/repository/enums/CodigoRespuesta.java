package org.examen.pruebateamsoftegb.repository.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CodigoRespuesta {
    CODE_0 ("0", "Operacion con exito.");

    private String code;
    private String label;
}
