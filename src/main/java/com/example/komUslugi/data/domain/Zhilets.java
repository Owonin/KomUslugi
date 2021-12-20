package com.example.komUslugi.data.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Zhilets")
@Data
@NoArgsConstructor
public class Zhilets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Kod_zhiltsa;
    @Column(name = "FIO")
    private String FIO;
    @Column(name = "Nomer_litsevogo_scheta")
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
    @OneToOne(mappedBy = "zhilets")
    private User user;

    @Override
    public String toString() {
        return "Zhilets{" +
                "Kod_zhiltsa=" + Kod_zhiltsa +
                ", FIO='" + FIO + '\'' +
                ", nomerLitsevogoScheta=" + nomerLitsevogoScheta +
                ", nomerTelefona='" + nomerTelefona + '\'' +
                ", electronnayaPochta='" + electronnayaPochta + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Zhilets)) return false;
        Zhilets zhilets = (Zhilets) o;
        return nomerLitsevogoScheta == zhilets.nomerLitsevogoScheta &&
                Objects.equals(Kod_zhiltsa, zhilets.Kod_zhiltsa) &&
                Objects.equals(FIO, zhilets.FIO) &&
                Objects.equals(nomerTelefona, zhilets.nomerTelefona) &&
                Objects.equals(electronnayaPochta, zhilets.electronnayaPochta) &&
                Objects.equals(user, zhilets.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Kod_zhiltsa, FIO, nomerLitsevogoScheta, nomerTelefona, electronnayaPochta, user);
    }
}
