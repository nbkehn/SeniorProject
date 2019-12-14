package bco.scheduler.repository;

import bco.scheduler.model.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface CarrierRepository extends JpaRepository<Carrier, Long>{
    /**
     * Get carrier by name using a LIKE query
     * @param name name of carrier
     * @return carrier with name given
     */
    List<Carrier> findByNameLike(String name);
}