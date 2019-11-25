package atmsimulation.services;

import atmsimulation.model.Account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadCSVImpl implements main.java.atmsilmulation.services.ReadCSV {

	@Override
	public void readRecord() {

		List<Account> users = Account.getInstance();

		try {
			String filepath = "data.csv";
			File file = new File(filepath);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			List<String> data = new ArrayList<>();
			while (line != null) {

				data.add(line);

				String[] record = line.split("\\,");

				users.add(new Account(record[0], record[1], record[2], record[3]));
				line = reader.readLine();
			}
			reader.close();
			validateRecord();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	

	@Override
	public void validateRecord() {
		
		List<Account> users = Account.getInstance();
		for (int i = 0; i < users.size() - 1; i++) {
			for (int j = i + 1; j < users.size(); j++) {
				if (users.get(i).getAccountNumber().equals(users.get(j).getAccountNumber()) && users.get(i).getBalance().equals(users.get(j).getBalance())
						&& users.get(i).getName().equals(users.get(j).getName()) && users.get(i).getPin().equals(users.get(j).getPin())) {
					System.out.println("Can't have duplicated record in csv file");
					System.exit(0);
				}
				if (users.get(i).getAccountNumber().equals(users.get(j).getAccountNumber())) {
					System.out.println("Can't have 2 different account with the same Account Number in the csv file");
					System.exit(0);
				}
				
			}
		}
		
	}
}
