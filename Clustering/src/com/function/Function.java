package com.function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Function implements Runnable {

	String filePath;
	String opPath;
	BufferedWriter bufferedWriter = null;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOpPath() {
		return opPath;
	}

	public void setOpPath(String opPath) {
		this.opPath = opPath;
	}

	@Override
	public void run() {
		try {
			File file = new File(filePath);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String data;
			String key = "";
			boolean firstLine = true;
			List<String> values = null;
			while ((data = bufferedReader.readLine()) != null) {
				if (firstLine) {
					key = data.split("&&")[0];
					firstLine = false;
					values = new ArrayList<>();
				}
				if (data.split("&&")[0].equals(key)) {
					values.add(data.split("&&")[1]);
				} else {
					flushData(applyFunction(key, values));
					key = data.split("&&")[0];
					values = new ArrayList<>();
					values.add(data.split("&&")[1]);

				}
			}
			flushData(applyFunction(key, values));
			bufferedReader.close();
			bufferedWriter.close();
			Path filePath = Paths.get(file.getPath());
			Files.delete(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void flushData(@SuppressWarnings("rawtypes") Map map) {
		try {
			File file = new File(opPath);

			if (!file.exists())
				bufferedWriter = new BufferedWriter(new FileWriter(new File(opPath)));
			for (Object key : map.keySet()) {
				bufferedWriter.write(key + "&&" + map.get(key) + "\n");
			}
			bufferedWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected abstract Map<String, String> applyFunction(String k, List<String> values);

}
