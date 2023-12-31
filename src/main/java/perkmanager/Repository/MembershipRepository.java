package perkmanager.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import perkmanager.Entity.Membership;

import java.util.List;

@Repository
public interface MembershipRepository extends CrudRepository<Membership, Long>{

    @Override
    List<Membership> findAll();

    Membership findById(long id);

    Membership findByName(String name);

    Membership findByImagePath(String imagePath);

}
