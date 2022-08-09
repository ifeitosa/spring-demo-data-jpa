package com.example.demo.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.EntidadeForte;
import com.example.demo.model.EntidadeFraca;


@SpringBootTest
public class RepositorioEntidadeForteTest {
    
    private final RepositorioEntidadeForte repositorio;
    private RepositorioEntidadeFraca fracas;

    public RepositorioEntidadeForteTest(@Autowired RepositorioEntidadeForte repositorio, @Autowired RepositorioEntidadeFraca fracas) {
        this.repositorio = repositorio;
        this.fracas = fracas;
    }

    @Test
    @Transactional
    public void inserirEntidadeForte() {
        this.repositorio.deleteAll();

        EntidadeForte forte = new EntidadeForte();
        ArrayList<EntidadeFraca> entidadesFracas = new ArrayList<>();
        entidadesFracas.add(new EntidadeFraca(null, "valor 1"));
        entidadesFracas
        .add(new EntidadeFraca(null, "valor 2"));
        entidadesFracas
        .add(new EntidadeFraca(null, "valor 3"));
        forte.setEntidadesFracas(entidadesFracas);

        forte.setEntidadesFracas(this.fracas.saveAll(entidadesFracas));
        this.repositorio.save(forte);

        Optional<EntidadeForte> forte2 = this.repositorio.findById(forte.getId());
        System.out.println("Entidade recuperada: ");
        System.out.println(forte2.get());
        System.out.println("Entidade original: ");
        System.out.println(forte);
        assertEquals(forte2.get(), forte);
        
    }

}
