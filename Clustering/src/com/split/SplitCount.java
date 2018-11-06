package com.split;

public class SplitCount extends SplitText {

	public SplitCount(String filePath, double numSplits) {
		super(filePath, numSplits);
	}

	@Override
	String getData(String data) {
		// TODO Auto-generated method stub
		String key = (data.split("\\,")[2]).replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
		return key + "&&" + 1;
	}

}
