package com.javawhizz.App;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    int amountOfTags = 100;
    @GetMapping("/tags/{amount}")
    public ResponseEntity<?> checkNumber(@PathVariable int amount){
        System.out.println("tags amount");
        if (amount != amountOfTags){
            amountOfTags = amount;
        }
        return ResponseEntity.ok("Updated or same");
    }
}
