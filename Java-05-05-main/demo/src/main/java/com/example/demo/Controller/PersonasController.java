package com.example.demo.Controller;

import com.example.demo.Modelo.Persona;
import com.example.demo.Repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (para evitar CORS)
public class PersonasController {



    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Persona> getPersonaById(@PathVariable String id) {
        if(id != null && id.length() > 20) {
            return personaRepository.findById(id);
        } else if () {
            
        }
        return null;
    }

    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {

        return personaRepository.save(persona);
    }

    @DeleteMapping("/{id}")
    public void deletePersona(@PathVariable String id) {

        personaRepository.deleteById(id);
    }
}
