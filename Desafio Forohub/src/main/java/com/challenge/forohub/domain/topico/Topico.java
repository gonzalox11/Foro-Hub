package com.challenge.forohub.domain.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaDeCreacion;
    private String status;
    private Boolean activo;
    private String autor;
    private String curso;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaDeCreacion = datosRegistroTopico.fechaDeCreacion();
        this.status = datosRegistroTopico.status();
        this.activo = true;
        this.autor = datosRegistroTopico.autor();
        this.curso = datosRegistroTopico.curso();
    }

    public void actualizarDatos(@Valid DatosActualizarTopico datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
    }

    public void eliminarTopico(Long id) {
        this.activo = false;
    }
}
