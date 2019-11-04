package atmsilmulation.services;

import atmsilmulation.model.Account;
import atmsilmulation.model.History;
import atmsilmulation.repository.AccountRepository;
import atmsilmulation.repository.HistoryRepository;
import atmsilmulation.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class FundTransferServicesImpl implements FundTransferServices {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	HistoryRepository historyRepository;

	@Override
	public void inputDestinationAccountScreen() {

		List<Account> users = Account.getInstance();
		String regex = "[0-9]+";
		FundTransferScreen2 transferScreen2 = new FundTransferScreen2();
		FundTransferScreen1 transferScreen1 = new FundTransferScreen1();
		Scanner scan = new Scanner(System.in);
		FundTransferScreen1.destinationAcc = scan.nextLine();

		if (!FundTransferScreen1.destinationAcc.matches(regex)) {
			System.out.println("Invalid account");
			transferScreen1.show();
		}

		if (isExists(users)) {
			transferScreen2.show();
		} else {
			System.out.println("Invalid account");
			transferScreen1.show();
		}
	}

	@Override
	public void inputAmountScreen() {
		
		String regex = "^[1-9][0-9]*$";
		Scanner scan = new Scanner(System.in);
		FundTransferScreen3 transferScreen3 = new FundTransferScreen3();
		FundTransferScreen2 transferScreen2 = new FundTransferScreen2();
		FundTransferScreen1.tranferAmt = scan.nextLine();

		if (!FundTransferScreen1.tranferAmt.matches(regex)) {
			System.out.println("Invalid ammount");
			transferScreen2.show();
		} else if (Integer.parseInt(FundTransferScreen1.tranferAmt) > 1000) {
			System.out.println("Maximum amount to transfer is $1000");
			transferScreen2.show();
		} else if (WelcomeScreen.balance - Integer.parseInt(FundTransferScreen1.tranferAmt) < 0) {
			System.out.println("Insufficient balance $" + FundTransferScreen1.tranferAmt);
			transferScreen2.show();
		} else if (Integer.parseInt(FundTransferScreen1.tranferAmt) < 1) {
			System.out.println("Minimum amount to transfer is $1");
		} else {
			transferScreen3.show();
		}
	}

	@Override
		
	public void inputReferenceNumberScreen() {
		
		FundTransferScreen4 fundTransferScreen4 = new FundTransferScreen4();
		FundTransferScreen3 fundTransferScreen3 = new FundTransferScreen3();
		String regex = "[0-9]+";
		Scanner scan = new Scanner(System.in);
		FundTransferScreen1.referenceNum = scan.nextLine();

		if (!FundTransferScreen1.referenceNum.matches(regex) && !FundTransferScreen1.referenceNum.isEmpty()) {
			System.out.println("Invalid Reference Number");
			fundTransferScreen3.show();
		} else {
			
			fundTransferScreen4.show();
		}

	}

	@Override
	public void transferConfirmScreen(FundTransferScreen1 fundTransferScreen, List<Account> users) {

		Scanner scan = new Scanner(System.in);
		String opt = scan.nextLine();

		switch (opt) {
		case "1":
			FundTransferSummaryScreen fundTransferSummaryScreen = new FundTransferSummaryScreen();
			TransactionHistory transactionHistory = new TransactionHistoryImpl();
			WelcomeScreen.balance -= Integer.parseInt(FundTransferScreen1.tranferAmt);
			addAmountToDestination(users);
			reduceBalance();
			updateCSV(users);
			transactionHistory.addFundTransferHistory();
			fundTransferSummaryScreen.show();
			break;
		case "2":
			TransactionScreen transactionScreen = new TransactionScreen();
			transactionScreen.show();
			break;
		case "":
			fundTransferScreen.show();
			break;
		default:
			break;
		}
	}

	@Override
	public void transaction(TransactionScreen transactionScreen) {

		transactionScreen.show();
		
	}

	@Override
	public boolean isExists(List<Account> users) {
		boolean isExists = false;
		for (int i = 0; i < users.size(); i++) {
			if (FundTransferScreen1.destinationAcc.equals(users.get(i).getAccountNumber())) {
				isExists = true;
				break;
			} else {
				isExists = false;
			}
		}
		return isExists;
	}

	@Override
	public void addAmountToDestination(List<Account> users) {
		for (int i = 0; i < users.size(); i++) {
			if (FundTransferScreen1.destinationAcc.equals(users.get(i).getAccountNumber())){
				int temp = Integer.parseInt(users.get(i).getBalance()) + Integer.parseInt(FundTransferScreen1.tranferAmt);
				users.get(i).setBalance(String.valueOf(temp));
				break;
			}
		}
	}

	@Override
	public String submitFundTransaction(String accountNumber, String pin, String destination, int amount, String ref) {
		Account currentAccount = accountRepository.findAccountByAccountNumberAndPin(accountNumber, pin);
		Account destinationAccount = accountRepository.findAccountByAccountNumber(destination);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		if (currentAccount == null || destinationAccount == null) {
			return "Account is invalid";
		}
		if (Integer.parseInt(currentAccount.getBalance()) <= amount) {
			return "Insufficient balance $" + amount;
		}
		if (amount > 1000) {
			return "Maximum amount to transfer is $1000";
		}

		currentAccount.setBalance(Integer.toString(Integer.parseInt(currentAccount.getBalance()) - amount));
		destinationAccount.setBalance(Integer.toString(Integer.parseInt(destinationAccount.getBalance()) + amount));
		accountRepository.save(currentAccount);
		accountRepository.save(destinationAccount);
		History history = new History();
		history.setAccNumber(currentAccount.getAccountNumber());
		history.setBalance(currentAccount.getBalance());
		history.setAmount(String.valueOf(amount));
		history.setRefNumber(ref);
		history.setDestinationAcc(destinationAccount.getAccountNumber());
		history.setDate(LocalDateTime.now().format(formatter));
		history.setType("FUND_TRANSFER");
		historyRepository.save(history);
		return "SUCCESS";
}


	@Override
	public void updateCSV(List<Account> users) {
		
		try {
			FileWriter writer = new FileWriter("data.csv",false);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			PrintWriter printWriter = new PrintWriter(bufferedWriter);
			List<String> list = new ArrayList<>();
			for (int i = 0; i < users.size(); i++) {
				list.add(users.get(i).getName() + "," + users.get(i).getPin() + "," + users.get(i).getBalance()+ "," + users.get(i).getAccountNumber());
			}
			
			String collect = list.stream().collect(Collectors.joining("\r"));
			
			printWriter.print(collect);
			printWriter.flush();
			printWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void reduceBalance() {
		List<Account> users = Account.getInstance();
		for (int i = 0; i < users.size(); i++) {
			if (WelcomeScreen.accNumberStatic.equals(users.get(i).getAccountNumber())
					&& WelcomeScreen.pinStatic.equals(users.get(i).getPin())) {
				users.get(i).setBalance(String.valueOf(WelcomeScreen.balance));
			}
		}
		
	}
}
