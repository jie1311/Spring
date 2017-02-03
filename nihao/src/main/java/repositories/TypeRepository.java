package repositories;


import java.util.List;
import entities.Type;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TypeRepository extends MongoRepository<Type, String> {

    public List<Type> findByManufacturer(String manufacturer);

}