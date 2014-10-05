package com.amit.salestax.calculator;

import com.amit.salestax.calculator.business.SalesTaxCalculator;
import com.amit.salestax.calculator.entity.Item;
import com.amit.salestax.calculator.util.ConsoleReader;
import com.amit.salestax.calculator.util.Util;

public class ReceiptGenerator {
	
	public static void main(String[] args) {
		try {
			doCalculation();
		} catch (Exception e) {
			System.err.println("-----Some exception occured... exiting app-----");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private static void doCalculation() throws Exception {
		System.out.println("Please enter description, to end enter empty string: \n");
		ConsoleReader consoleReader = ConsoleReader.getInstance();
		StringBuilder output = new StringBuilder();
		double totalTax = 0;
		double totalCost = 0;
		String statement;
		while(! (statement=consoleReader.readLine()) .equals("")) {
			Item item = Item.createItem(statement);
			double tax = SalesTaxCalculator.getInstance().calculateTax(item);
			totalTax += tax;
			double itemCost = SalesTaxCalculator.getInstance().calculateItemCost(item, tax);
			totalCost += itemCost;
			output.append(item.receiptString(itemCost));
		}
		output.append("=====================================\n");
		output.append("Total Cost\t: " + Util.formattedDecimalNumber(totalCost) + "\n");
		output.append("Sales Tax Paid\t: " + Util.formattedDecimalNumber(totalTax));
		System.out.println(output);
	}

}
