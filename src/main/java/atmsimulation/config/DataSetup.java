package atmsimulation.config;

import atmsimulation.model.Account;
import atmsimulation.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DataSetup implements ApplicationListener {

    @Autowired
    AccountRepository accountRepository;


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (accountRepository.findAccountByAccountNumber("159509") == null) {
            accountRepository.save(new Account("Hazard", "159509", "112233", "50"));
        }
        if (accountRepository.findAccountByAccountNumber("363068") == null) {
            accountRepository.save(new Account("Cole", "363068", "112233", "120"));
        }
        if (accountRepository.findAccountByAccountNumber("919307") == null) {
            accountRepository.save(new Account("Mata", "919307", "112233", "60"));
        }

    }
}
