package com.splitclass;

import com.split.SplitText;

public class SplitInnerJoin extends SplitText {

	public SplitInnerJoin(String filePath, double numSplits) {
		super(filePath, numSplits);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getData(String data) {
		String key = (data.split("\\;")[2]).replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
		return key + "&&" + "file1||" + data;
	}

}
