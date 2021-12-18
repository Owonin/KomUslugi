package com.example.komUslugi.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Kommunalnye_uslugi")
@Data
@NoArgsConstructor
public class Komm_uslugi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Kod_usligi;
    @Column(name = "Vid_uslug", nullable = false)
    private String vidUslug;
    @Column(name = "Stoimost_uslug", nullable = false)
    private int stoimostUslug;
    @Column(name = "Edinitsa_izmereniya")
    private String edinitsaIzmereniya;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Oplata> oplataSet;

}
