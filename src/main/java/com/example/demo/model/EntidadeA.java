package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "relacao")
@ToString
@Entity
@Table(name = "entidade_A")
public class EntidadeA {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    
    @OneToMany(mappedBy = "entidadeA")
    private Set<ABRelation> relacao = new HashSet<>();

    public void conectar(EntidadeB b, Double quantidade) {
        this.relacao.add(new ABRelation(null, quantidade, LocalDateTime.now(), null, this, b));
    }

    public void desconecat(EntidadeB b) {
        this.relacao = this.relacao.stream().filter(ab -> !ab.getEntidadeB().equals(b)).collect(Collectors.toSet());
    }
    
}
