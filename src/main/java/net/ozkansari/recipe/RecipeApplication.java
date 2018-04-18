package net.ozkansari.recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipeApplication {

    public static void main(String[] args) {
        System.out.println("Running...");
        SpringApplication.run(RecipeApplication.class, args);
    }
}
