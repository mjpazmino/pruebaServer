package ec.edu.espe.pruebaserver.dao;

import ec.edu.espe.pruebaserver.model.Paralelo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ParaleloRepository extends MongoRepository<Paralelo, String> {

  Optional<Paralelo> findByNumeroAndNivel(Integer numero, Integer nivel);

  List<Paralelo> findByNivel(Integer nivel);
}
