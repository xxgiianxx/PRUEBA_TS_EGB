package org.examen.pruebateamsoftegb.repository;

import org.examen.pruebateamsoftegb.model.Evento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface EventoRepository extends ReactiveCrudRepository<Evento, Long> {
}
