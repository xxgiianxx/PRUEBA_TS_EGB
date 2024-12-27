package org.examen.pruebateamsoftegb.business;

import org.examen.pruebateamsoftegb.expose.request.EventoRequest;
import org.examen.pruebateamsoftegb.model.Evento;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventoService {
  Mono<Evento> createEvento(EventoRequest eventoRequest);
  Mono<Evento> getEventoById(Long id);
  Flux<Evento> getAllEventos();
  Mono<Evento> updateEvento(Long id, EventoRequest eventoRequest);
  Mono<Void> deleteEvento(Long id);
}
