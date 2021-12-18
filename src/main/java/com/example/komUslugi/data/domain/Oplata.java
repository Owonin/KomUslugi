package com.example.komUslugi.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Oplata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Komm_uslugi usluga;
    @ManyToOne
    private UprOrganizatsiya org;
    @ManyToOne
    private Zhilets zhilets;
    @ManyToOne
    private Zhiloe_pomeshenie zhiloe_pomeshenie;

    @Column(name = "Summa_k_oplate", nullable = false)
    private int summaKOplate;
    @Column(name = "Pokazanya_schetchika")
    private int pokazanyaSchetchika;
    @Column(name = "Sostoyanie_oplati", nullable = false)
    private Boolean Sostoyanie_oplati;
    @Column(name = "Data_oplati")
    @Temporal(TemporalType.DATE)
    private Date dataOplati;
    @Column(name = "Krainiy_srok_oplati", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date Krainiy_srok_oplati;
    @Column(name = "Summa_peni", nullable = false)
    private int Summa_peni;
}
