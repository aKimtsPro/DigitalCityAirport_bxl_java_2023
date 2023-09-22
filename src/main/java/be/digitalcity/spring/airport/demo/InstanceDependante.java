package be.digitalcity.spring.airport.demo;

import org.springframework.stereotype.Component;

@Component
public class InstanceDependante {


    // POUR UNE INJECTION DE DéPENDANCE
    // 1 - La classe dépendente doit produire un Bean
    // 2 - l'instance dont la classe est dépendante doit exister sous former de Bean
    // 3 - la dépendance doit être déclarée (ctor, @Autowired)

    // @Autowired
    private final AInstancier aInstancier;

    public InstanceDependante(AInstancier aInstancier) {
        this.aInstancier = aInstancier;
    }

}
