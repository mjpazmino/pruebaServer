package ec.edu.espe.pruebaserver.dao;

import ec.edu.espe.pruebaserver.model.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends MongoRepository<Estudiante, String> {

    Optional<Estudiante> findByCedula(String cedula);

    Optional<Estudiante> findByCedulaAndNivel(String cedula, Integer nivel);

    List<Estudiante> findByNivel(Integer nivel);
}
