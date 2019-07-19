package com.studycase1.atmsilmulation.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.studycase1.atmsilmulation.model.History;
import com.studycase1.atmsilmulation.view.FundTransferScreen1;
import com.studycase1.atmsilmulation.view.WelcomeScreen;
import com.studycase1.atmsilmulation.view.WithdrawScreen;

public class TransactionHistoryImpl implements TransactionHistory {

	@Override
	public void addWithdrawHistory() {

		try {
			FileWriter writer;
			writer = new FileWriter("WithdrawHistory.csv", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			PrintWriter printWriter = new PrintWriter(bufferedWriter);

			Calendar cal = Calendar.getInstance();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			String collect = "Withdraw History," + dtf.format(now) + "," + WelcomeScreen.accNumberStatic + ","
					+ WithdrawScreen.withdrawAmount + "," + "$" + WelcomeScreen.balance;

			printWriter.print(collect);
			printWriter.append("\n");
			printWriter.flush();
			printWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addFundTransferHistory() {

		try {
			FileWriter writer;
			writer = new FileWriter("FundTransferHistory.csv", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			PrintWriter printWriter = new PrintWriter(bufferedWriter);

			Calendar cal = Calendar.getInstance();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			String collect = "Fund Transfer History," + dtf.format(now) + "," + WelcomeScreen.accNumberStatic + ","
					+ FundTransferScreen1.destinationAcc + "," + "$" + FundTransferScreen1.tranferAmt + ","
					+ FundTransferScreen1.referenceNum + "," + "$" + WelcomeScreen.balance;

			printWriter.print(collect);
			printWriter.append("\n");
			printWriter.flush();
			printWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void readWithdrawHistoryCSV() {

		History history = new History();

		try {
			String filepath = "WithdrawHistory.csv";
			File file = new File(filepath);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			List<String> data = new ArrayList<>();
			while (line != null) {

				data.add(line);

				String[] record = line.split("\\,");
				
				if (WelcomeScreen.accNumberStatic.equals(record[2])) {

					history.setType(record[0]);
					history.setDate(record[1]);
					history.setAccNumber(record[2]);
					history.setWithdrawAtm(record[3]);
					history.setBalance(record[4]);
					
					printWithdrawHistory(history);
				}
				
				line = reader.readLine();
			}
			reader.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public void printWithdrawHistory(History history) {

		System.out.println(history.getType());
		System.out.println("Date: "+ history.getDate());
		System.out.println("Account Number: " + history.getAccNumber());
		System.out.println("Withdraw Amount: " + history.getWithdrawAtm());
		System.out.println("Balance: " + history.getBalance());
		System.out.println("=============================");
		
	}


	@Override
	public void readTransferHistoryCSV() {

		History history = new History();

		// Read WithdrawCSV
		try {
			String filepath = "FundTransferHistory.csv";
			File file = new File(filepath);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			List<String> data = new ArrayList<>();
			while (line != null) {

				data.add(line);

				String[] record = line.split("\\,");
				
				if (WelcomeScreen.accNumberStatic.equals(record[2])) {

					history.setType(record[0]);
					history.setDate(record[1]);
					history.setAccNumber(record[2]);
					history.setDestinationAcc(record[3]);
					history.setTransferAtm(record[4]);
					history.setRefNumber(record[5]);
					history.setBalance(record[6]);
					
					printTransferHistory(history);
				}
				
				line = reader.readLine();
			}
			reader.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}

	@Override
	public void printTransferHistory(History history) {
			
		System.out.println(history.getType());
		System.out.println("Date: "+ history.getDate());
		System.out.println("Account Number: " + history.getAccNumber());
		System.out.println("Destination Account Number: " + history.getDestinationAcc());
		System.out.println("Transfer Amount: " + history.getTransferAtm());
		System.out.println("Reference Number: " + history.getRefNumber());
		System.out.println("Balance: " + history.getBalance());
		System.out.println("=============================");
		
	}

}
