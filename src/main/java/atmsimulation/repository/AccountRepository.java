package atmsimulation.repository;


        import atmsimulation.model.Account;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountByAccountNumber(String accountNumber);

    Account findAccountByAccountNumberAndPin(String accountNumber, String pin);

    List<Account> findAll();

}