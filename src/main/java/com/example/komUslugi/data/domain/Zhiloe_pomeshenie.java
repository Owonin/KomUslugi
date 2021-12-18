package com.example.komUslugi.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "zhiloe_pomeshenie")
@Data
@NoArgsConstructor
public class Zhiloe_pomeshenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Kod_zhilogo_pomesheniya;
    @Column(name = "Litsevoy_schet_zh_pom", nullable = false)
    private int litsevoySchetZhPom;
    //@Column(name = "Gorod_zh_pom", nullable = false)
    //private String gorodZhPom;
    //@Column(name = "Ylitsa_zh_pom", nullable = false)
   // private String ylitsaZhPom;
    //@Column(name = "Nomer_doma_zh_pom", nullable = false)
   // private String nomerDomaZhPom;
    @Column(name = "adress_doma_zh_pom")
    private String adressDomaZhPom;
    @Column(name = "Kolichestvo_zhiltsov", nullable = false)
    private int kolichestvoZhiltsov;
    @Column(name = "Ploshad", nullable = false)
    private String ploshad;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Oplata> oplataSet;
    @ManyToMany(mappedBy = "zhiloePomeshenieSet")
    private Set<Zhilets> zhiletsSet;
}
