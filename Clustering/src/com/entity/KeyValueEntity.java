package com.entity;

import java.io.BufferedReader;

public class KeyValueEntity implements Comparable<KeyValueEntity> {

	String data;
	String value;
	String key;
	BufferedReader bufferedReaderValue;

	public BufferedReader getBufferedReaderValue() {
		return bufferedReaderValue;
	}

	public void setBufferedReaderValue(BufferedReader bufferedReaderValue) {
		this.bufferedReaderValue = bufferedReaderValue;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int compareTo(KeyValueEntity o) {
		if (this.key.hashCode() > o.key.hashCode())
			return 1;
		else if (this.key.hashCode() < o.key.hashCode())
			return -1;
		return 0;
	}

	public KeyValueEntity(String data, BufferedReader bufferedReaderValue) {
		super();
		this.data = data;
		this.bufferedReaderValue = bufferedReaderValue;
		try {
			String values[] = data.split("&&");
			this.key = values[0];
			this.value = values[1];
		} catch (Exception e) {
			System.out.println("Here");
		}
	}

	@Override
	public String toString() {
		return "EntityTest [key=" + key + "]";
	}

	public String getValue() {
		return value;
	}

	public String getKey() {
		return key;
	}

}
