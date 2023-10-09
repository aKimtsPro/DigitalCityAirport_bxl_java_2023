package be.digitalcity.spring.airport.bl.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("helloService1")
public class HelloService {

    public enum Langue{
        EN,
        FR,
        ES
    }

    public String getSalutation(Langue langue){
        Assert.notNull(langue, "la langue ne peut pas être null");

        return switch (langue){
            case EN -> "Hello world!";
            case FR -> "Bonjour monde!";
            case ES -> "Hola Mundo!";
        };
    }

}
