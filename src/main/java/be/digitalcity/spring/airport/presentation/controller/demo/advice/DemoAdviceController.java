package be.digitalcity.spring.airport.presentation.controller.demo.advice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoAdviceController {

    @GetMapping("/demo/throw/1")
    public void throwError(){
        throw new IllegalArgumentException();
    }

}
