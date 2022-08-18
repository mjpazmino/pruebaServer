package ec.edu.espe.pruebaserver.resource;

import ec.edu.espe.pruebaserver.model.Estudiante;
import ec.edu.espe.pruebaserver.model.Paralelo;
import ec.edu.espe.pruebaserver.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/estudiante")
@RequiredArgsConstructor
public class EstudianteResource {
  private final EstudianteService estudianteService;

  @PostMapping
  public ResponseEntity<Estudiante> crearEstudiante(@RequestBody Estudiante estudiante) {
    Estudiante nuevoEstudiante = this.estudianteService.crear(estudiante);
    return ResponseEntity.ok(nuevoEstudiante);
  }

  @GetMapping(path = "/{nivel}")
  public ResponseEntity<List<Estudiante>> listarPorNivel(@PathVariable Integer nivel) {
    List<Estudiante> estudiantes = this.estudianteService.listarPorNivel(nivel);
    return ResponseEntity.ok(estudiantes);
  }

  @PutMapping(path = "/{cedula}/{nivel}")
  public ResponseEntity<Paralelo> asignarEstudianteAParalelo(@PathVariable String cedula, @PathVariable Integer nivel) {
    Paralelo paraleloDB = this.estudianteService.asignarEstudianteAParalelo(cedula, nivel);
    return ResponseEntity.ok(paraleloDB);
  }
}
