package perkmanager.Repository;

import org.springframework.data.repository.CrudRepository;
import perkmanager.Entity.Person;

import java.util.List;


public interface PersonRepository extends CrudRepository<Person, Long>{

    @Override
    List<Person> findAll();

    Person findById(long id);

    Person findByUsername(String username);
}
