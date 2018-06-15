package org.ncomet.fibonaccispringaop.resource;

import org.ncomet.fibonaccispringaop.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@RestController
@RequestMapping("/fibonacci/{n}")
public class FibonacciResource {

    private final FibonacciService fibonacciService;

    @Autowired
    public FibonacciResource(FibonacciService fibonacciService) {
        this.fibonacciService = fibonacciService;
    }

    @GetMapping
    public ResponseEntity<String> fibo(@PathVariable int n) {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        long result = fibonacciService.compute(n);
        stopWatch.stop();

        long duration = stopWatch.getLastTaskTimeMillis();
        return ResponseEntity.ok(MessageFormat.format(
                "fibonacci({0}) = {1}<br/> temps de calcul <font color=\"red\">{2}</font>ms <br/><br/>",
                n,
                result,
                duration)
        + commentary(duration));
    }





    private String commentary(long duration) {
        return duration > 1L ? "Un peu <i>long</i> non?" : "Wow, c'était <i>rapide</i> !";
    }
}
