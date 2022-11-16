package delegate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetClass {
    @GetMapping("/")
    public String greet() {
        return "Greeter class for database";
    }

    @GetMapping("/admin")
    public String greetAdmin() {
        return "hello admin";
    }

    @GetMapping("/user")
    public String greetUser() {
        return "hello user";
    }
}
