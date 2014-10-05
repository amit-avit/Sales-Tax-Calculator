package com.amit.salestax.calculator.business;

import com.amit.salestax.calculator.entity.Item;
import com.amit.salestax.calculator.util.ExemptedGoods;
import com.amit.salestax.calculator.util.Util;

public class SalesTaxCalculator {
	
	private static SalesTaxCalculator _instance;
	
	private SalesTaxCalculator() {
		
	}
	
	public static SalesTaxCalculator getInstance() {
		if(_instance == null) {
			_instance = new SalesTaxCalculator();
		}
		return _instance;
	}
	
	public double calculateTax(Item item) throws Exception {
		int taxRate = 0;
		if(! ExemptedGoods.getInstance().contains(item.getItemName())) {
			taxRate += 10;
		}
		if(item.isImported()) {
			taxRate += 5;
		}
		double tax = item.getQuantity() * item.getPricePerUnit() * taxRate / 100.0;
		tax = Util.roundOffToPoint05(tax);
		return tax;
	}
	
	public double calculateItemCost(Item item, double tax) {
		return item.getQuantity() * item.getPricePerUnit() + tax;
	}

}
