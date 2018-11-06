package com.split;

public class SplitInnerJoin extends SplitText {

	public SplitInnerJoin(String filePath, double numSplits) {
		super(filePath, numSplits);
		// TODO Auto-generated constructor stub
	}

	@Override
	String getData(String data) {
		String key = (data.split("\\,")[6]).replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
		return key + "&&" + "file1||" + data;
	}

}
