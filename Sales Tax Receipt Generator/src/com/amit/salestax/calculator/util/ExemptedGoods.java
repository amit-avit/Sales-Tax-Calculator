package com.amit.salestax.calculator.util;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;


public class ExemptedGoods {
	
	private static final String EXEMPTED_GOODS_FILE_NAME = "exempted_goods.properties";
	private static ExemptedGoods _instance;
	
	private List<String> exemptedGoods;
	
	private ExemptedGoods() {
	}
	
	public static ExemptedGoods getInstance() throws Exception {
		if(_instance == null) {
			_instance = new ExemptedGoods();
			List<String> exemptedGoods = readExemptedGoods();
			_instance.exemptedGoods = exemptedGoods;
		}
		return _instance;
	}

	private static List<String> readExemptedGoods() throws Exception {
		Properties properties = new Properties();
		properties.load(new FileReader(EXEMPTED_GOODS_FILE_NAME));
		String goodsString = "";
		for (Entry<Object, Object> entry: properties.entrySet()) {
			goodsString += entry.getValue() + ",";
		}
		String[] goodsArray = goodsString.split(" *, *");
		List<String> goodsList = Arrays.asList(goodsArray);
		return goodsList;
	}
	
	public boolean contains(String goodToSearch) {
		for (String exemptedGood : exemptedGoods) {
			if (goodToSearch.contains(exemptedGood)) {
				return true;
			}
		}
		return false;
	}

}
