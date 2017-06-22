package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
@RefreshScope
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting/{name}")
    public Greeting greeting(@PathVariable String name) throws InterruptedException {
    	//new Thread().sleep(5000);
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
