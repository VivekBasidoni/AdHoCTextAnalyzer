package com.function;

import java.io.File;

import com.driver.MainJobClass;

public class FunctionJob {
	public void applyFunction(Class<? extends Function> function) {
		File file = new File(MainJobClass.filePath);
		int fCount = 0;
		ThreadGroup group = null;
		for (File f : file.listFiles()) {
			group = new ThreadGroup("Apply Function");
			Function functions = null;
			try {
				functions = function.newInstance();
				functions.setFilePath(f.getAbsolutePath());
				functions.setOpPath(MainJobClass.filePath + "\\Output" + fCount++ + System.nanoTime() + ".txt");
				new Thread(group, functions).start();
			} catch (InstantiationException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
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
	}
}
