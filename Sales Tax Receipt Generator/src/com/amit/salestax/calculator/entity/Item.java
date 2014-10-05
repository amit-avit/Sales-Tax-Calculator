package com.amit.salestax.calculator.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amit.salestax.calculator.util.Util;

public class Item {
	private static Pattern quantityPattern = Pattern.compile("[0-9]+ ");
	private static Pattern pricePerUnitPattern = Pattern.compile("[0-9]+\\.[0-9]+");
	
	private int quantity;
	private String itemName;
	private double pricePerUnit;
	private boolean imported;
	
	private Item(int quantity, String itemName, double pricePerUnit,
			boolean imported) {
		super();
		this.quantity = quantity;
		this.itemName = itemName;
		this.pricePerUnit = pricePerUnit;
		this.imported = imported;
	}

	public static Item createItem(String description) throws Exception {
		Matcher quantityMatcher = quantityPattern.matcher(description);
		Matcher priceMatcher = pricePerUnitPattern.matcher(description);
		if (quantityMatcher.find() && priceMatcher.find()) {
			String quantityString = quantityMatcher.group();
			int quantity = Integer.parseInt(quantityString.substring(0, quantityString.length()-1));
			boolean imported = description.contains("imported");
			int itemNameStart = imported ? description.indexOf("imported")+9 : quantityMatcher.end();
			int itemNameEnd = description.indexOf(" at") + 1;
			String itemName = description.substring(itemNameStart, itemNameEnd);
			String priceString = priceMatcher.group();
			double pricePerUnit = Double.parseDouble(priceString);
			Item item = new Item(quantity, itemName, pricePerUnit, imported);
			return item;
		} else {
			throw new Exception("Exception occured... The sentence is malformed: " + description);
		}
	}
	
	public String receiptString(double cost) {
		return quantity + " " + itemName + "\t: " + Util.formattedDecimalNumber(cost) + "\n";
	}

	public int getQuantity() {
		return quantity;
	}

	public String getItemName() {
		return itemName;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public boolean isImported() {
		return imported;
	}

}
