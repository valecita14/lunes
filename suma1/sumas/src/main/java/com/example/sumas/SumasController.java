package com.example.sumas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SumasController {

    @GetMapping("/sumarDos")
    public int sumarDosNumeros(@RequestParam int num1, @RequestParam int num2) {
        return num1 + num2;
    }

    @GetMapping("/sumarTres")
    public int sumarTresNumeros(@RequestParam int num1, @RequestParam int num2, @RequestParam int num3) {
        return num1 + num2 + num3;
    }
}