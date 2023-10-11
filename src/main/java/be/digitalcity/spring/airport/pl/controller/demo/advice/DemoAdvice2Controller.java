package be.digitalcity.spring.airport.pl.controller.demo.advice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoAdvice2Controller {

    @GetMapping("/demo/throw/2")
    public void throwError(){
        throw new IllegalArgumentException();
    }

}
