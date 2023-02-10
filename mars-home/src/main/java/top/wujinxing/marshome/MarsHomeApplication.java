package top.wujinxing.marshome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.wujinxing.marshome.mapper")
public class MarsHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarsHomeApplication.class, args);
	}

}
