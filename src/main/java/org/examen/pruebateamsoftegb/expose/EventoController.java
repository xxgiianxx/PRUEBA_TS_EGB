package org.examen.pruebateamsoftegb.expose;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.examen.pruebateamsoftegb.business.EventoService;
import org.examen.pruebateamsoftegb.expose.request.EventoRequest;
import org.examen.pruebateamsoftegb.expose.response.EventoResponse;
import org.examen.pruebateamsoftegb.model.Evento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
@Slf4j
public class EventoController {

    private final EventoService eventoService;

    @PostMapping
    public Mono<ResponseEntity<EventoResponse>> register(@Valid @RequestBody EventoRequest eventoRequest) {
        log.info("Solicitud recibida: {}", eventoRequest);
        return eventoService.createEvento(eventoRequest)
                .map(evento -> new ResponseEntity<>(new EventoResponse(evento.getEventoid(), evento.getNombre()), HttpStatus.CREATED))
                .doOnError(e -> log.error("Error al crear el evento: ", e));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Evento>> getEventoById(@PathVariable Long id) {
        return eventoService.getEventoById(id)
                .map(evento -> ResponseEntity.ok(evento))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<Evento> getAllEventos() {
        return eventoService.getAllEventos();
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Evento>> updateEvento(@PathVariable Long id, @Valid @RequestBody EventoRequest eventoRequest) {
        return eventoService.updateEvento(id, eventoRequest)
                .map(updatedEvento -> ResponseEntity.ok(updatedEvento))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Flux<Evento>>> deleteEvento(@PathVariable Long id) {
        return eventoService.deleteEvento(id)
                .thenMany(eventoService.getAllEventos())
                .collectList()
                .map(eventos -> ResponseEntity.ok(Flux.fromIterable(eventos)))
                .defaultIfEmpty(ResponseEntity.noContent().build());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
            log.error("Error de validación en el campo: {} - {}", error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralExceptions(Exception ex) {
        log.error("Error general: ", ex);
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
