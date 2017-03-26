package net.gustavohenrique.spotippos;

import static spark.Spark.port;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Main {
    public static void main(String[] args) {
    	port(9999);
    	new AnnotationConfigApplicationContext("net.gustavohenrique.spotippos").start();
    }
}
