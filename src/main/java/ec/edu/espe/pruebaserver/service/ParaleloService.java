package ec.edu.espe.pruebaserver.service;

import ec.edu.espe.pruebaserver.dao.ParaleloRepository;
import ec.edu.espe.pruebaserver.exception.NotFoundException;
import ec.edu.espe.pruebaserver.model.Estudiante;
import ec.edu.espe.pruebaserver.model.Paralelo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParaleloService {
  private final ParaleloRepository paraleloRepository;

  private void verificarEquilibrioParalelos(Integer paralelo, Integer nivel) {
    Optional<Paralelo> paraleloOpt = this.paraleloRepository.findByNumeroAndNivel(paralelo, nivel);
    if (paraleloOpt.isPresent()) {
      Paralelo paraleloDB = paraleloOpt.get();
      List<Paralelo> paralelosPorNivel = this.paraleloRepository.findByNivel(nivel);
    }
  }

  public List<Estudiante> listarEstudiantesPorNivelYParalelo(Integer nivel, Integer paralelo) {
    Optional<Paralelo> paraleloOpt = this.paraleloRepository.findByNumeroAndNivel(paralelo, nivel);
    if (!paraleloOpt.isPresent()) {
      throw new NotFoundException(
          "No se encuentra el paralelo indicado");
    }
    Paralelo paraleloDB = paraleloOpt.get();
    return paraleloDB.getEstudiantesList();
  }
}
