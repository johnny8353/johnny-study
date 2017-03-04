package com.zte.msm.jetty;

public class StartUI {
	public static void main(String[] args) throws Exception {
//		new JettyStartEmbedded(8088, System.getProperty("user.dir")+"/src/main/webapp").start();
		new JettyStartEmbedded(80, "ui", System.getProperty("user.dir") + "/src/main/webapp").start();
	}
}
