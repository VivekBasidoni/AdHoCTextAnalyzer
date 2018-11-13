package com.externalsort;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import com.driver.MainJobClass;
import com.shuffle.ShuffleKeys;

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
		mergeTwoFiles(file);
		shuffleKeys(file.listFiles()[0].getPath());
		long end = System.currentTimeMillis();
		System.out.println("Took : " + ((end - start) / 1000));

	}

	private void shuffleKeys(String string) {
		ThreadGroup group = new ThreadGroup("Shuffle group");
		ShuffleKeys keys = new ShuffleKeys(string);
		new Thread(group, keys).start();
		while (group.activeCount() >= 1) {
			try {
				Thread.sleep(100);
				System.out.println("----------------   Waiting to finish the thread    -------------------");
				System.out.println(group.activeCount() + " Still pending");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

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
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void mergeTwoFiles(File file) {
		int fileCount = 0;
		while (file.listFiles().length != 1) {
			fileCount++;
			int rCount = 0;
			File fileOne = null;
			ThreadGroup combineGroup = new ThreadGroup("Combine group");
			Collection<File> files = Collections.unmodifiableCollection(Arrays.asList(file.listFiles()));
			System.out.println(files.size() + "-------------------------------------------------");
			for (File f : files) {
				if (rCount % 2 == 0) {
					fileOne = f;
				} else {
					File fi = new File(MainJobClass.filePath + "\\" + fileCount + System.nanoTime() + ".txt");
					MergeTwoFiles mergeTwoFiles = new MergeTwoFiles(fileOne, f, fi);
					new Thread(combineGroup, mergeTwoFiles).start();
				}
				rCount++;
			}
			while (combineGroup.activeCount() >= 1) {
				try {
					Thread.sleep(100);
					System.out.println("----------------   Waiting to finish the thread    -------------------");
					System.out.println(combineGroup.activeCount() + " Still pending");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (file.listFiles().length == 1)
				break;
		}
	}

}