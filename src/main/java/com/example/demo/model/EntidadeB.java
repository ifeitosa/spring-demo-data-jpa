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
@EqualsAndHashCode(exclude = "relacao")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entidade_B")
public class EntidadeB {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    @OneToMany(mappedBy = "entidadeB")
    private Set<ABRelation> relacao = new HashSet<>();

    public void conectar(EntidadeA a, Double quantidade) {
        this.relacao.add(new ABRelation(null, quantidade, LocalDateTime.now(), null, a, this));
    }

    public void desconectar(EntidadeA a) {
        this.relacao = this.relacao.stream().filter(ab -> !ab.getEntidadeA().equals(a)).collect(Collectors.toSet());
    }
    
}
