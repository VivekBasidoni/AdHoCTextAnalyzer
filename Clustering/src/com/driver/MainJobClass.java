package com.driver;

import java.io.File;

import com.externalsort.SortAndMerge;
import com.function.FunctionJob;
import com.functionclass.FunctionGroupBy;
import com.functionclass.FunctionInnerJoin;
import com.split.SplitTextJob;
import com.splitclass.SplitCount;
import com.splitclass.SplitInnerJoin;
import com.splitclass.SplitInnerJoin2;

public class MainJobClass {
	public final static String filePath = "File" + System.nanoTime();

	public static void main(String[] args) {
		//innerJoin();
		 groupBySum();
	}

	private static void groupBySum() {
		new File(filePath).mkdir();
		SplitTextJob splitTextJob = new SplitTextJob("City.csv");
		splitTextJob.splitTextJob(SplitCount.class);
		SortAndMerge externalSort = new SortAndMerge();
		externalSort.run();
		FunctionJob functionTest = new FunctionJob();
		functionTest.applyFunction(FunctionGroupBy.class);
	}

	private static void innerJoin() {
		new File(filePath).mkdir();
		SplitTextJob splitTextJob = new SplitTextJob("City.csv");
		splitTextJob.splitTextJob(SplitInnerJoin.class);
		SplitTextJob splitTextJob1 = new SplitTextJob("Country.csv");
		splitTextJob1.splitTextJob(SplitInnerJoin2.class);
		SortAndMerge externalSort = new SortAndMerge();
		externalSort.run();
		FunctionJob functionTest = new FunctionJob();
		functionTest.applyFunction(FunctionInnerJoin.class);
	}
}
