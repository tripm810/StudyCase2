package atmsimulation.repository;


import atmsimulation.model.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends CrudRepository<History, Integer> {

    List<History> findByAccNumber(String accountNumber);
}