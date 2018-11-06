package com.shuffle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ShuffleKeys implements Runnable {
	String filePath;
	boolean writeFile = false;

	public ShuffleKeys(String filePath) {
		super();
		this.filePath = filePath;
	}

	@Override
	public void run() {
		File file = new File(filePath);
		int fileCount = 0;

		Scanner scanner = null;
		BufferedWriter out = null;
		FileWriter fstream1 = null;
		try {
			scanner = new Scanner(file);
			fstream1 = new FileWriter(file.getParentFile().getAbsolutePath() + "\\file" + fileCount++ + ".txt");
			out = new BufferedWriter(fstream1);
			String lineOne = null;
			double counter = 0;
			String data;
			while (scanner.hasNextLine()) {
				data = scanner.nextLine();
				if (counter < 1000) {
					out.write(data + "\n");
				} else if (counter == 1000) {
					lineOne = data.split("&&")[0];
					out.write(data + "\n");
				} else {
					if (!(lineOne.equals(data.split("&&")[0]))) {
						out.flush();
						fstream1 = new FileWriter(
								file.getParentFile().getAbsolutePath() + "\\file" + fileCount++ + ".txt");
						out = new BufferedWriter(fstream1);
						lineOne = data.split("&&")[0];
						counter = 0;
					}
					out.write(data + "\n");
				}
				counter++;
				if (counter % 1000 == 0) {
					out.flush();
				}
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();
		file.delete();
	}

}
