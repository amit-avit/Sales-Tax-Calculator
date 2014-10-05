package com.amit.salestax.calculator.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {
	
	private static ConsoleReader _instance;
	
	private BufferedReader reader;
	
	private ConsoleReader() {
	}
	
	public static ConsoleReader getInstance() {
		if(_instance == null) {
			_instance = new ConsoleReader();
			_instance.reader = new BufferedReader(new InputStreamReader(System.in));
		}
		return _instance;
	}
	
	public String readLine() throws IOException {
			return reader.readLine();
	}

}
