package com.studycase1.atmsilmulation;

import com.studycase1.atmsilmulation.services.ReadCSV;
import com.studycase1.atmsilmulation.services.ReadCSVImpl;
import com.studycase1.atmsilmulation.view.WelcomeScreen;

public class AtmSilmulation {

	public static void main(String[] args) {

		ReadCSV readCSV = new ReadCSVImpl();
		readCSV.readRecord();

		WelcomeScreen welcomeScreen = new WelcomeScreen();
		while (true) {
		welcomeScreen.show();
		}
	}

}
