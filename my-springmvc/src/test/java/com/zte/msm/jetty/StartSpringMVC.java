package com.zte.msm.jetty;

public class StartSpringMVC {
	public static void main(String[] args) throws Exception {
//		new JettyStartEmbedded(8088, System.getProperty("user.dir")+"/src/main/webapp").start();
		new JettyStartEmbedded(8080, "spring", System.getProperty("user.dir") + "/src/main/webapp").start();
	}
}
