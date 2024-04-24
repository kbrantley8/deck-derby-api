package deckderby;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class TempController {

    private static final Logger LOG = LogManager.getLogger();

    @GetMapping("/hello")
    public String hello() {
//        return "Hello, World!";
        LOG.info("GOT HERE");
        return "{\"message\": \"Hello, world!\"}";
    }
}
