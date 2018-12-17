package com.splitclass;

import com.split.SplitText;

public class SplitCount extends SplitText {

	public SplitCount(String filePath, double numSplits) {
		super(filePath, numSplits);
	}

	@Override
	public String getData(String data) {
		// TODO Auto-generated method stub
		String key = (data.split("\\;")[2]).replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
		return key + "&&" + 1;
	}

}
