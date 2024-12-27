package org.examen.pruebateamsoftegb.impl;

import lombok.RequiredArgsConstructor;
import org.examen.pruebateamsoftegb.business.EventoService;
import org.examen.pruebateamsoftegb.expose.request.EventoRequest;
import org.examen.pruebateamsoftegb.model.Evento;
import org.examen.pruebateamsoftegb.repository.EventoRepository;
import org.examen.pruebateamsoftegb.repository.ParticipanteRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;
    private final ParticipanteRepository participanteRepository;

    @Override
    public Mono<Evento> createEvento(EventoRequest eventoRequest) {
        return participanteRepository.findById(eventoRequest.getParticipanteid())
                .switchIfEmpty(Mono.error(new RuntimeException("Participante no existe")))
                .flatMap(participante -> {
                    Evento evento = Evento.builder()
                            .nombre(eventoRequest.getNombre())
                            .tipoevento(eventoRequest.getTipoevento())
                            .participanteid(eventoRequest.getParticipanteid())
                            .fechaevento(eventoRequest.getFechaevento())
                            .lugar(eventoRequest.getLugar())
                            .build();
                    return eventoRepository.save(evento);
                });
    }

    @Override
    public Mono<Evento> getEventoById(Long id) {
        return eventoRepository.findById(id);
    }

    @Override
    public Flux<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public Mono<Evento> updateEvento(Long id, EventoRequest eventoRequest) {
        return eventoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Evento no encontrado")))
                .flatMap(existingEvento -> participanteRepository.findById(eventoRequest.getParticipanteid())
                        .switchIfEmpty(Mono.error(new RuntimeException("Participante no existe")))
                        .flatMap(participante -> {
                            existingEvento.setNombre(eventoRequest.getNombre());
                            existingEvento.setTipoevento(eventoRequest.getTipoevento());
                            existingEvento.setParticipanteid(eventoRequest.getParticipanteid());
                            existingEvento.setFechaevento(eventoRequest.getFechaevento());
                            existingEvento.setLugar(eventoRequest.getLugar());
                            return eventoRepository.save(existingEvento);
                        }));
    }

    @Override
    public Mono<Void> deleteEvento(Long id) {
        return eventoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Evento no encontrado")))
                .flatMap(eventoRepository::delete);
    }

}
