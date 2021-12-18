package com.example.komUslugi.data.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Zhilets")
@Data
@NoArgsConstructor
public class Zhilets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Kod_zhiltsa;
    @Column(name = "FIO", nullable = false)
    private String FIO;
    @Column(name = "Nomer_litsevogo_scheta", nullable = false)
    private int nomerLitsevogoScheta;
    @Column(name = "Nomer_telefona")
    private String nomerTelefona;
    @Column(name = "Electronnaya_pochta")
    private String electronnayaPochta;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "prozhiveanie", joinColumns = @JoinColumn(name = "kod_zhiltsa"),
            inverseJoinColumns = @JoinColumn(name = "kod_zhilogo_pomesheniya"))
    private Set<Zhiloe_pomeshenie> zhiloePomeshenieSet;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Oplata> oplataSet;
}
