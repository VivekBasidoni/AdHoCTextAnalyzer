package com.externalsort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MergeTwoFiles implements Runnable {
	File fileOne;
	File fileTwo;
	File result;
	BufferedReader bufferedReaderOne;
	BufferedReader bufferedReaderTwo;
	BufferedWriter bufferedWriter;

	public MergeTwoFiles(File fileOne, File fileTwo, File result) {
		super();
		this.fileOne = fileOne;
		this.fileTwo = fileTwo;
		this.result = result;
	}

	@Override
	public void run() {
		try {
			result.createNewFile();
			bufferedReaderOne = new BufferedReader(new FileReader(fileOne));
			bufferedReaderTwo = new BufferedReader(new FileReader(fileTwo));
			bufferedWriter = new BufferedWriter(new FileWriter(result));
			String rowOne = null;
			String rowTwo = null;
			rowOne = bufferedReaderOne.readLine();
			rowTwo = bufferedReaderTwo.readLine();
			int counter = 0;
			while (true) {
				if (rowOne == null) {
					if (rowTwo != null)
						bufferedWriter.write(rowTwo + "\n");
					writeremainingFile(bufferedReaderTwo);
					break;
				} else if (rowTwo == null) {
					if (rowOne != null)
						bufferedWriter.write(rowOne + "\n");
					writeremainingFile(bufferedReaderOne);
					break;
				} else {
					if (rowOne.split("&&")[0].compareTo(rowTwo.split("&&")[0]) < 0) {
						bufferedWriter.write(rowOne + "\n");
						rowOne = bufferedReaderOne.readLine();
					} else if (rowOne.split("&&")[0].compareTo(rowTwo.split("&&")[0]) > 0) {
						bufferedWriter.write(rowTwo + "\n");
						rowTwo = bufferedReaderTwo.readLine();
					} else {
						bufferedWriter.write(rowOne + "\n");
						bufferedWriter.write(rowTwo + "\n");
						rowOne = bufferedReaderOne.readLine();
						rowTwo = bufferedReaderTwo.readLine();
					}

				}
				counter++;
				if (counter == 50) {
					bufferedWriter.flush();
					counter = 0;
				}
			}
			bufferedWriter.flush();
			bufferedWriter.close();
			bufferedReaderOne.close();
			bufferedReaderTwo.close();
			fileOne.delete();
			fileTwo.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void writeremainingFile(BufferedReader bufferedReader) {
		String lineVal;
		try {
			while ((lineVal = bufferedReader.readLine()) != null) {
				bufferedWriter.write(lineVal + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
