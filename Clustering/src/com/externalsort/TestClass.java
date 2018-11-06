package com.externalsort;

import java.io.IOException;

import com.function.FunctionInnerJoin;
import com.function.FunctionTest;
import com.split.SplitInnerJoin;
import com.split.SplitInnerJoin2;
import com.split.SplitTextJob;

public class TestClass {

	public static void main(String[] args) throws IOException, InterruptedException {
		SplitTextJob splitTextJob = new SplitTextJob("Files//1500000 Sales Records.csv");
		splitTextJob.splitTextJob(SplitInnerJoin.class);
		SplitTextJob splitTextJob1 = new SplitTextJob("Files//1500000 Sales Records_1.csv");
		splitTextJob1.splitTextJob(SplitInnerJoin2.class);
		SortAndMerge externalSort = new SortAndMerge();
		externalSort.run();
		FunctionTest functionTest = new FunctionTest();
		functionTest.applyFunction(FunctionInnerJoin.class);
	}
}
