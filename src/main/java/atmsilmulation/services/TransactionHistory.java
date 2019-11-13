package atmsilmulation.services;


import atmsilmulation.model.History;

import java.util.List;

public interface TransactionHistory {

	List<History> findByAccNumber (String accountNumber);
	
	
}
