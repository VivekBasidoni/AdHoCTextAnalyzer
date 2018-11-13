package com.split;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.driver.MainJobClass;

public abstract class SplitText implements Runnable {
	String inputfile;
	double nol;
	static int fileCount = 0;

	public SplitText(String filePath, double numSplits) {
		super();
		this.inputfile = filePath;
		this.nol = numSplits;
	}

	public void run() {
		File file = new File(inputfile);
		try {
			Scanner scanner = new Scanner(file, "utf-8");
			int count = 0;
			while (scanner.hasNext()) {
				scanner.nextLine();
				count++;
			}
			System.out.println("Lines in the file: " + count); // Displays no. of lines in the input file.

			double temp = (count / nol);
			int temp1 = (int) temp;
			int nof = 0;
			if (temp1 == temp) {
				nof = temp1;
			} else {
				nof = temp1 + 1;
			}
			System.out.println("No. of files to be generated :" + nof); // Displays no. of files to be generated.

			// ---------------------------------------------------------------------------------------------------------

			// Actual splitting of file into smaller files

			FileInputStream fstream = new FileInputStream(inputfile);
			DataInputStream in = new DataInputStream(fstream);

			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			for (int j = 1; j <= nof; j++) {
				FileWriter fstream1 = new FileWriter(MainJobClass.filePath + "\\" + fileCount + j + ".txt");
				BufferedWriter out = new BufferedWriter(fstream1);
				for (int i = 1; i <= nol; i++) {
					strLine = br.readLine();
					if (strLine != null) {
						out.write(getData(strLine));
						if (i != nol) {
							out.newLine();
						}
					}
				}
				out.close();
			}

			in.close();
			scanner.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		fileCount++;
	}

	public abstract String getData(String data);

}
