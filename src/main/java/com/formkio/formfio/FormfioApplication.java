package com.formkio.formfio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Usually, this should all there is. Unless there is more specific initalizers, then declare that in
// a method which could be called easily instead and then call it in the main().
@SpringBootApplication
public class FormfioApplication {
    // What is happening here is probably just using .class to get all declared methods and such.
    public static void main(String[] args) {
        startSpring(args);
    }

    public static void startSpring(String[] args) {
        SpringApplication.run(FormfioApplication.class, args);
    }

}
