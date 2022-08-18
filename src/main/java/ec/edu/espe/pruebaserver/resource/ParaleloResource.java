package ec.edu.espe.pruebaserver.resource;

import ec.edu.espe.pruebaserver.model.Estudiante;
import ec.edu.espe.pruebaserver.service.ParaleloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/paralelo")
@RequiredArgsConstructor
public class ParaleloResource {

  private final ParaleloService paraleloService;

  @GetMapping(path = "/{nivel}/{paralelo}")
  public ResponseEntity<List<Estudiante>> listarEstudiantesPorNivelYParalelo(@PathVariable Integer nivel,@PathVariable Integer paralelo) {
    List<Estudiante> estudiantes = this.paraleloService.listarEstudiantesPorNivelYParalelo(nivel, paralelo);
    return ResponseEntity.ok(estudiantes);
  }
}
