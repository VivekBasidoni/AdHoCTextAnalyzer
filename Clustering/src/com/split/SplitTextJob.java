package com.split;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SplitTextJob {

	public SplitTextJob(String files) {
		super();
		this.files = files;
	}

	String files;

	public void splitTextJob(Class<? extends SplitText> splitData) {
		ThreadGroup group = new ThreadGroup("Split Text");
		try {
			Constructor<?> cons = splitData.getConstructors()[0];
			SplitText splitText = (SplitText) cons.newInstance(files, 1000);
			new Thread(group, splitText).start();
			while (group.activeCount() >= 1) {
				try {
					Thread.sleep(100);
					System.out.println("----------------   Waiting to finish the thread    -------------------");
					System.out.println(group.activeCount() + " Still pending");
					System.out.println(group.getName() + " Running");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
