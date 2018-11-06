package com.split;

public class SplitInnerJoin2 extends SplitText {

	public SplitInnerJoin2(String filePath, double numSplits) {
		super(filePath, numSplits);
		// TODO Auto-generated constructor stub
	}

	@Override
	String getData(String data) {
		String key = (data.split("\\,")[6]).replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
		return key + "&&" + "file2||" + data;
	}

}
