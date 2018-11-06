package com.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class FunctionInnerJoin extends Function {

	@Override
	protected Map<String, String> applyFunction(String k, List<String> values) {
		Map<String, String> map = new HashMap<>();
		List<String> file1Data = new ArrayList<>();
		List<String> file2Data = new ArrayList<>();
		for (String string : values) {
			if (string.split(Pattern.quote("||"))[0].equals("file1")) {
				file1Data.add(string.split(Pattern.quote("||"))[1]);
			} else {
				file2Data.add(string.split(Pattern.quote("||"))[1]);
			}
		}
		String data = "";
		for (String s1 : file1Data) {
			for (String s2 : file2Data) {
				data = data + s1 + "," + s2 + "\n";
			}
		}
		if (!data.equals(""))
			map.put(k, data.substring(0, data.length() - 1));
		return map;
	}

}
