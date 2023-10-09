package be.digitalcity.spring.airport.presentation.controller;

import be.digitalcity.spring.airport.models.entity.Person;
import be.digitalcity.spring.airport.bl.service.impl.HelloService;
import be.digitalcity.spring.airport.models.form.DemoForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {


    private final String[] values = { "bonjour", "au revoir", "adieu" };
    private final HelloService helloService;

    // Le nom du paramètre peut servir à choisir le Bean dans le cas d'une multiplicité.
    public DemoController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String getHello(){
        return helloService.getSalutation(HelloService.Langue.EN);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/request")
    public void getRequest(HttpServletRequest request){
        System.out.println(request.getMethod());
        System.out.println(request.getRequestURI());
    }


//    @RequestMapping(method=RequestMethod.POST, path= "/body")
    @PostMapping("/body")
    // @RequestBody permet de recupérer le body de la requete sous la forme d'une objet java
    // Le body doit correspondre au type de l'objet
    public void getBody(@RequestBody Person person){
        System.out.println(person);
    }


    @GetMapping("/param")
    // @RequestParam permet de recupérer un paramètre de la requête
    public void getParam(@RequestParam("valeur1") int a,@RequestParam int b){
        int rslt = a+b;
        System.out.println(a + "+" + b + "=" + rslt);
    }

    @GetMapping("/param/all")
    // Récupère tous les params
    public void getParams(@RequestParam Map<String, Object> params){
        params.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    @GetMapping("/pathvar/{index:^[0-9]+$}")
    // Récupère une variable de chemin
    // La valeur de chemin peut être limitée via une regex
    public void getPathVar( @PathVariable("index") int i ){
        System.out.println( values[i] );
    }

    @GetMapping("/header")
    // Récupère un header par rapport à son nom
    public void getHeader(@RequestHeader("Mon-Header") String header){
        System.out.println(header);
    }

    @GetMapping("/header/all")
    public void getHeaders(@RequestHeader HttpHeaders headers){
        System.out.println(headers);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @GetMapping("/response/simple")
    public Person getPerson(){
        Person p = new Person();
        p.setFirstname("luc");
        p.setLastname("passant");
        return p;
    }

    @GetMapping("/response/better")
    public ResponseEntity<Person> getRespEntityPerson(){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Mon-Header", "saValeur");

        Person body = new Person();
        body.setFirstname("luc");
        body.setLastname("passant");

        HttpStatus status = HttpStatus.I_AM_A_TEAPOT;

//        return new ResponseEntity<>(body, headers, status);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("Mon-Header", "saValeur1", "saValeur2")
                .body(body);
    }


    @PostMapping("/validation")
    public ResponseEntity<?> demoValidation(@Valid @RequestBody DemoForm form){
        return ResponseEntity.ok().build();
    }

}
