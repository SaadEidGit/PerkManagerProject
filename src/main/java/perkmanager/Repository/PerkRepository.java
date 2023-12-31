package perkmanager.Repository;

import org.springframework.data.repository.CrudRepository;
import perkmanager.Entity.Perk;

import java.util.List;

public interface PerkRepository extends CrudRepository<Perk, Long> {

    @Override
    List<Perk> findAll();

    Perk findById(long id);

    Perk findByPerkName(String perkName);
}
