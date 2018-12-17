package com.externalsort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import com.driver.MainJobClass;
import com.entity.KeyValueEntity;

public class PrioritySort extends Thread {

	@Override
	public void run() {
		int outCounter = 0;
		FileWriter fstream1 = null;
		try {
			File fileFolder = new File(MainJobClass.filePath);
			BufferedReader[] bufferedReaders = new BufferedReader[fileFolder.listFiles().length];
			PriorityQueue<KeyValueEntity> priorityQueue = new PriorityQueue<>(fileFolder.listFiles().length);

			int fileCount = 0;
			List<File> files = Collections.unmodifiableList(Arrays.asList(fileFolder.listFiles()));
			for (File file : files) {
				bufferedReaders[fileCount++] = new BufferedReader(new FileReader(file));
			}
			fileCount = 0;
			fstream1 = new FileWriter(MainJobClass.filePath + "\\file" + fileCount++ + ".csv");
			BufferedWriter bufferedWriter = new BufferedWriter(fstream1);
			String lineOne = null;
			while (true) {
				if (priorityQueue.isEmpty()) {
					for (BufferedReader bufferedReader : bufferedReaders) {
						priorityQueue.add(new KeyValueEntity(bufferedReader.readLine(), bufferedReader));
					}
				}
				KeyValueEntity entityKeyValue = priorityQueue.poll();
				StringBuilder sb = new StringBuilder(4096);
				sb.append(entityKeyValue.getBufferedReaderValue().readLine());
				if (!sb.toString().equals("null"))
					priorityQueue.add(new KeyValueEntity(sb.toString(), entityKeyValue.getBufferedReaderValue()));
				if (outCounter < 1000) {
					bufferedWriter.write(entityKeyValue.getData() + "\n");
				} else if (outCounter == 1000) {
					lineOne = entityKeyValue.getKey();
					bufferedWriter.write(entityKeyValue.getData() + "\n");
				} else {
					if (!(lineOne.equals(entityKeyValue.getKey()))) {
						bufferedWriter.flush();
						fstream1 = new FileWriter(MainJobClass.filePath + "\\file" + fileCount++ + ".csv");
						bufferedWriter = new BufferedWriter(fstream1);
						lineOne = entityKeyValue.getKey();
						outCounter = 0;
					}
					bufferedWriter.write(entityKeyValue.getData() + "\n");
				}
				outCounter++;
				if (outCounter % 1000 == 0) {
					bufferedWriter.flush();
				}
				int cnt = 0;
				if (priorityQueue.isEmpty()) {
					for (File file : files) {
						bufferedReaders[cnt++].close();
						file.delete();
					}
					bufferedWriter.flush();
					bufferedWriter.close();
					return;
				}
			}

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}