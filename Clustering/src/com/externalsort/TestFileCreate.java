package com.externalsort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestFileCreate {

	public static void main(String[] args) throws IOException {
	createFile();

	}

	private static void createFile() throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("InputBig.csv")));
		String data = ",address2,address22,district2,null,postal_code2,phone2,location2,last_update2";
		Random rand = new Random();
		long al = 1000000000000L;
		while (true) {
			bufferedWriter.write(rand.nextLong() + data + "\n");
			al = al - 1;
			if (al == 0)
				break;
			if (al % 1000 == 0)
				bufferedWriter.flush();
		}
		bufferedWriter.flush();
		bufferedWriter.close();
	}

}
