package ec.edu.espe.pruebaserver.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApplicationValues {
    private final String mongoHost;
    private final String mongoDB;
    private final String mongoUsr;
    private final String mongoPwd;
    private final String mongoAut;

    public ApplicationValues(
            @Value("${prueba.server.mongo.host}") String mongoHost,
            @Value("${prueba.server.mongo.db}") String mongoDB,
            @Value("${prueba.server.mongo.usr}") String mongoUsr,
            @Value("${prueba.server.mongo.pwd}") String mongoPwd,
            @Value("${prueba.server.mongo.aut}") String mongoAut) {

        this.mongoHost = mongoHost;
        this.mongoDB = mongoDB;
        this.mongoUsr = mongoUsr;
        this.mongoPwd = mongoPwd;
        this.mongoAut = mongoAut;
    }
}
