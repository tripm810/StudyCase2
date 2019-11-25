package atmsimulation.services;


import atmsimulation.model.History;

import java.util.List;

public interface TransactionHistory {

	List<History> findByAccNumber (String accountNumber);
	
	
}
