package com.zte.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */

@SpringBootApplication
//@ComponentScan("com.zte.com")
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

}


