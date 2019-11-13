package atmsilmulation.services;

import atmsilmulation.model.History;
import atmsilmulation.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHistoryImpl implements TransactionHistory {

	@Autowired
	HistoryRepository historyRepository;

	@Override
	public List<History> findByAccNumber(String accountNumber) {
		List<History> historyList = historyRepository.findByAccNumber(accountNumber);
		return historyList.size() >= 10
				? historyList.subList(historyList.size() - 10, historyList.size())
				: historyList;
	}

}
