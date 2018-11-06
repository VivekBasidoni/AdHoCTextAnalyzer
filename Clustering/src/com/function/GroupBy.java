package com.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupBy extends Function {

	@Override
	protected Map<String, String> applyFunction(String k, List<String> values) {
		Map<String, String> map = new HashMap<>();
		double sum = 0;
		for (String key : values) {
			sum += Double.parseDouble(key);
		}
		map.put(k, sum + "");
		return map;
	}

}
