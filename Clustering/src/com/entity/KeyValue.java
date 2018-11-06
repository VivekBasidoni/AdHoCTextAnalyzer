package com.entity;

import java.io.Serializable;
import java.util.List;

public final class KeyValue implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String key;
	private List<String> val;

	public String getKey() {
		return key;
	}

	public List<String> getVal() {
		return val;
	}

	@Override
	public String toString() {
		return "KeyValue [key=" + key + ", val=" + val + "]";
	}

}
