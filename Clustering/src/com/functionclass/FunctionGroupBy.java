package com.functionclass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.function.Function;

public class FunctionGroupBy extends Function {

	@Override
	protected Map<String, String> applyFunction(String k, List<String> values) {
		Map<String, String> map = new HashMap<>();
		double sum = 0;
		Double doubleVal;
		for (String vals : values) {
			try {
				doubleVal = Double.parseDouble(vals);
			} catch (NumberFormatException exception) {
				doubleVal = (double) 0;
			}
			sum += doubleVal;
		}
		map.put(k, sum + "");
		return map;
	}

}
