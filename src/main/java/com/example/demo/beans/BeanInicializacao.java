package com.example.demo.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.demo.model.EntidadeA;
import com.example.demo.model.EntidadeB;
import com.example.demo.model.EntidadeForte;
import com.example.demo.model.EntidadeFraca;
import com.example.demo.repo.RepositorioAB;
import com.example.demo.repo.RepositorioEntidadeA;
import com.example.demo.repo.RepositorioEntidadeB;
import com.example.demo.repo.RepositorioEntidadeForte;
import com.example.demo.repo.RepositorioEntidadeFraca;

@Component
public class BeanInicializacao {

    private final RepositorioEntidadeForte forte;

    private final RepositorioEntidadeFraca fraca;

    private RepositorioEntidadeB repoB;

    private RepositorioEntidadeA repoA;

    private RepositorioAB repoAB;
    
    public BeanInicializacao(RepositorioEntidadeForte forte, RepositorioEntidadeFraca fraca,
        RepositorioEntidadeA repoA, RepositorioEntidadeB repoB, RepositorioAB ab) {
        this.forte = forte;
        this.fraca = fraca;
        this.repoA = repoA;
        this.repoB = repoB;
        this.repoAB = ab;
    }

    @EventListener
    public void onStartup(WebServerInitializedEvent event) {
        this.fraca.deleteAll();
        this.forte.deleteAll();

        List<EntidadeFraca> itens1 = new ArrayList<>();
        itens1.add(new EntidadeFraca(null, "valor 1"));
        itens1.add(new EntidadeFraca(null, "valor 2"));
        itens1.add(new EntidadeFraca(null, "valor 3"));
        itens1 = this.fraca.saveAll(itens1);
        EntidadeForte f = new EntidadeForte(null, itens1);
        this.forte.save(f);

        List<EntidadeFraca> itens2 = new ArrayList<>();
        itens2.add(new EntidadeFraca(null, "item 1"));
        itens2.add(new EntidadeFraca(null, "item 2"));
        itens2.add(new EntidadeFraca(null, "item 3"));
        itens2.add(new EntidadeFraca(null, "item 4"));
        itens2 = this.fraca.saveAll(itens2);
        EntidadeForte g = new EntidadeForte(null, itens2);
        this.forte.save(g);

        this.repoAB.deleteAll();
        this.repoA.deleteAll();
        this.repoB.deleteAll();
        List<EntidadeA> as = new ArrayList<>();
        List<EntidadeB> bs = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < 10; i++) {
            as.add(new EntidadeA(null, Integer.toString(random.nextInt()), new HashSet<>()));
            bs.add(new EntidadeB(null, Integer.toString(random.nextInt()), new HashSet<>()));
        }
        as = this.repoA.saveAll(as);
        bs = this.repoB.saveAll(bs);
        for(int i = 0; i < 10; i++) {
            EntidadeA a0 = as.get(random.nextInt(9));
            EntidadeB b0 = bs.get(random.nextInt(9));
            a0.conectar(b0, random.nextDouble());
            this.repoAB.saveAll(a0.getRelacao());
            this.repoA.save(a0);
        }

    }
}
