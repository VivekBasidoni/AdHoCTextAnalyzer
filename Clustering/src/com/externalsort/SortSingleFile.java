package com.externalsort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SortSingleFile implements Runnable {

	public SortSingleFile(File inFile, File outFile) {
		super();
		this.inFile = inFile;
		this.outFile = outFile;
	}

	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	File inFile;
	File outFile;
	Map<String, List<String>> treeMap = new TreeMap<String, List<String>>();

	@Override
	public void run() {
		try {
			bufferedReader = new BufferedReader(new FileReader(inFile));
			String data;
			String key;
			String value;
			while ((data = bufferedReader.readLine()) != null) {
				key = data.split("&&")[0];
				value = data.split("&&")[1];
				if (treeMap.get(key) == null) {
					List<String> d = new ArrayList<>();
					d.add(value);
					treeMap.put(key, d);
				} else {
					treeMap.get(key).add(value);
				}
			}
			bufferedWriter = new BufferedWriter(new FileWriter(outFile));
			for (String s : treeMap.keySet()) {
				for (String sq : treeMap.get(s)) {
					bufferedWriter.write(s + "&&" + sq + "\n");
				}
			}
			bufferedWriter.flush();
			bufferedWriter.close();
			bufferedReader.close();
			inFile.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
