package ec.edu.espe.pruebaserver.service;

import ec.edu.espe.pruebaserver.dao.EstudianteRepository;
import ec.edu.espe.pruebaserver.dao.ParaleloRepository;
import ec.edu.espe.pruebaserver.exception.ConflictException;
import ec.edu.espe.pruebaserver.model.Estudiante;
import ec.edu.espe.pruebaserver.model.Paralelo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EstudianteService {

  private final EstudianteRepository estudianteRepository;
  private final ParaleloRepository paraleloRepository;

  private static final Integer LIMITE_NIVEL = 10;
  private static final Integer MAX_PARALELO = 3;
  private static final Integer MIN_PARALELO = 1;

  public Estudiante crear(Estudiante estudiante) {
    Optional<Estudiante> estudianteDB =
        this.estudianteRepository.findByCedula(estudiante.getCedula());
    if (estudianteDB.isPresent()) {
      throw new ConflictException(
          "No se ha podido crear el estudiante, ya existe un estudiante con la cédula "
              + estudiante.getCedula());
    }
    if (estudiante.getNivel() > LIMITE_NIVEL) {
      throw new ConflictException(
          "No se ha podido crear el estudiante, el nivel no puede ser mayor que 10");
    }
    return this.estudianteRepository.save(estudiante);
  }

  public Paralelo asignarEstudianteAParalelo(String cedula, Integer nivel) {
    Optional<Estudiante> estudianteOpt =
        this.estudianteRepository.findByCedulaAndNivel(cedula, nivel);
    if (!estudianteOpt.isPresent()) {
      throw new ConflictException(
          "No se puede asignar el estudiante, no esta registrado o no pertenece a ese nivel");
    }
    Optional<Paralelo> paraleloOpt =
            this.paraleloRepository.findByNumeroAndNivel(asignarParaleloRandom(),nivel);
    if (!paraleloOpt.isPresent()) {
      throw new ConflictException(
              "No se puede asignar el estudiante al paralelo");
    }
    Estudiante estudianteDB = estudianteOpt.get();
    Paralelo paraleloDB = paraleloOpt.get();
    paraleloDB.getEstudiantesList().add(estudianteDB);
    return this.paraleloRepository.save(paraleloDB);
  }

  private Integer asignarParaleloRandom(){
    Random rand = new Random();
    Integer paralelo = rand.nextInt((MAX_PARALELO - MIN_PARALELO) + 1) + MIN_PARALELO;
    return paralelo;
  }

  public List<Estudiante> listarPorNivel(Integer nivel) {
    return estudianteRepository.findByNivel(nivel);
  }
}
