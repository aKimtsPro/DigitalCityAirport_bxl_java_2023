package be.digitalcity.spring.airport.controller.demo.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "be.digitalcity.spring.airport.controller.demo.advice")
@Order(Ordered.HIGHEST_PRECEDENCE)
//@ControllerAdvice(assignableTypes = { DemoAdviceController.class, DemoAdvice2Controller.class })
public class DemoControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handle(){
        return ResponseEntity.badRequest()
                .body("from DemoControllerAdvice");
    }

}
