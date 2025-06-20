package com.example.demo.Repository;
import com.example.demo.Modelo.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends MongoRepository<Persona, String> {
    // Puedes agregar métodos personalizados si los necesitas


}


