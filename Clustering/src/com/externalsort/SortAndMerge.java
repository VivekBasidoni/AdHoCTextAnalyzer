package com.externalsort;

import java.io.File;
import java.io.IOException;

import com.driver.MainJobClass;

public class SortAndMerge {

	public SortAndMerge() {
		super();
	}

	public void run() {
		try {
			File file = new File(MainJobClass.filePath);
			sortFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sortFile(File file) throws IOException {
		long start = System.currentTimeMillis();
		sortFiles(file);
		PrioritySort prioritySort = new PrioritySort();
		Thread thread = new Thread(prioritySort);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("Took : " + ((end - start) / 1000));

	}

	private void sortFiles(File file) throws IOException {
		ThreadGroup group = new ThreadGroup("Sort group");
		for (File f : file.listFiles()) {
			File fi = new File(MainJobClass.filePath + "\\" + f.getName().split("\\.")[0] + "sorted.txt");
			fi.createNewFile();
			SortSingleFile sortSingleFile = new SortSingleFile(f, fi);
			new Thread(group, sortSingleFile).start();
		}

		while (group.activeCount() >= 1) {
			try {
				Thread.sleep(100);
				System.out.println("----------------   Waiting to finish the thread    -------------------");
				System.out.println(group.activeCount() + " Still pending");
				System.out.println(group.getName() + " Running");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}