package be.digitalcity.spring.airport;

import be.digitalcity.spring.airport.service.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class DigitalCityAirportApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalCityAirportApplication.class, args);
    }

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

//    @Bean(name = "helloService2")
//    public HelloService helloService(){
//        return new HelloService();
//    }

}
