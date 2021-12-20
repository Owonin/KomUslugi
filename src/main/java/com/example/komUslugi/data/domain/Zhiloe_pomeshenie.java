package com.example.komUslugi.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "zhiloe_pomeshenie")
@Data
@NoArgsConstructor
public class Zhiloe_pomeshenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Kod_zhilogo_pomesheniya;
    @Column(name = "Litsevoy_schet_zh_pom")
    private int litsevoySchetZhPom;
    @Column(name = "Gorod_zh_pom", nullable = false)
    private String gorodZhPom;
    @Column(name = "Ylitsa_zh_pom", nullable = false)
    private String ylitsaZhPom;
    @Column(name = "Nomer_doma_zh_pom", nullable = false)
   private String nomerDomaZhPom;
   @Transient
    private String adressDomaZhPom;
    @Column(name = "Ploshad")
    private String ploshad;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Oplata> oplataSet;
    @ManyToMany(mappedBy = "zhiloePomeshenieSet")
    private Set<Zhilets> zhiletsSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Zhiloe_pomeshenie)) return false;
        Zhiloe_pomeshenie that = (Zhiloe_pomeshenie) o;
        return litsevoySchetZhPom == that.litsevoySchetZhPom &&
                Objects.equals(Kod_zhilogo_pomesheniya, that.Kod_zhilogo_pomesheniya) &&
                Objects.equals(gorodZhPom, that.gorodZhPom) &&
                Objects.equals(ylitsaZhPom, that.ylitsaZhPom) &&
                Objects.equals(nomerDomaZhPom, that.nomerDomaZhPom) &&
                Objects.equals(adressDomaZhPom, that.adressDomaZhPom) &&
                Objects.equals(ploshad, that.ploshad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Kod_zhilogo_pomesheniya, litsevoySchetZhPom, gorodZhPom, ylitsaZhPom, nomerDomaZhPom, adressDomaZhPom, ploshad);
    }

    @Override
    public String toString() {
        return "Zhiloe_pomeshenie{" +
                "Kod_zhilogo_pomesheniya=" + Kod_zhilogo_pomesheniya +
                ", litsevoySchetZhPom=" + litsevoySchetZhPom +
                ", gorodZhPom='" + gorodZhPom + '\'' +
                ", ylitsaZhPom='" + ylitsaZhPom + '\'' +
                ", nomerDomaZhPom='" + nomerDomaZhPom + '\'' +
                ", adressDomaZhPom='" + adressDomaZhPom + '\'' +
                ", ploshad='" + ploshad + '\'' +
                '}';
    }
}
