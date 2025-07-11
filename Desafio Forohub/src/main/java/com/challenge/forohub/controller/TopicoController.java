package com.challenge.forohub.controller;

import com.challenge.forohub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name ="bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<RespuestaTopico> registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = repository.save(new Topico(datos));
        RespuestaTopico respuesta = new RespuestaTopico(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getAutor(),
                topico.getCurso());
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity listar(@PageableDefault (size = 10, sort = {"fechaDeCreacion"})Pageable paginacion) {
        var page = repository.findAllByActivoTrue(paginacion)
                .map(DatosListaTopico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizarTopico datos) {
        var topico = repository.getReferenceById(datos.id());
        topico.actualizarDatos(datos);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        topico.eliminarTopico(id);

        return ResponseEntity.noContent().build();
    }
}
