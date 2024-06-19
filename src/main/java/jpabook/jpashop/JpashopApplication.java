package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {


	@Bean
	Hibernate5JakartaModule hibernate5Module() {
		Hibernate5JakartaModule module = new Hibernate5JakartaModule();
		//강제 지연 로딩 설정 -> 대신 LAZY 강제 초기화 사용
		//module.configure(Hibernate5JakartaModule.Feature.FORCE_LAZY_LOADING, true);

		return module;
	}

	public static void main(String[] args) {

		LombokTest lombokTest=new LombokTest();
		lombokTest.setData("newData");
		String newData= lombokTest.getData();
		System.out.println("lombok test = "+newData);

		SpringApplication.run(JpashopApplication.class, args);
	}

}
