package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

		LombokTest lombokTest=new LombokTest();
		lombokTest.setData("newData");
		String newData= lombokTest.getData();
		System.out.println("lombok test = "+newData);

		SpringApplication.run(JpashopApplication.class, args);
	}

}
