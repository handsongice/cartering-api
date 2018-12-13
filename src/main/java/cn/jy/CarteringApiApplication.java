package cn.jy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.jy.*"})
@MapperScan("cn.jy.mapper")
public class CarteringApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarteringApiApplication.class, args);
    }

}

