package com.example.komUslugi.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "oplata")
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

    @Override
    public String toString() {
        return "Oplata{" +
                "id=" + id +
                ", usluga=" + usluga +
                ", org=" + org +
                ", zhilets=" + zhilets +
                ", zhiloe_pomeshenie=" + zhiloe_pomeshenie +
                ", summaKOplate=" + summaKOplate +
                ", Sostoyanie_oplati=" + Sostoyanie_oplati +
                ", pokazanyaSchetchika=" + pokazanyaSchetchika +
                ", dataOplati=" + dataOplati +
                ", krainiySrokOplati=" + krainiySrokOplati +
                '}';
    }

    @Column(name = "Summa_oplati")
    private int summaKOplate;
    @Column(name = "Sostoyanie_oplati")
    private boolean Sostoyanie_oplati;
    @Column(name = "Pokazanya_schetchika")
    private int pokazanyaSchetchika;
    @Column(name = "Data_oplati", columnDefinition = "DATE")
    private LocalDate dataOplati;
    @Column(name = "Krainiy_srok_oplati", columnDefinition = "DATE")
    private LocalDate krainiySrokOplati;

}
