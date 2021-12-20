package com.example.komUslugi.data.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "UprOrganizatsiya")
@Data
@NoArgsConstructor
public class UprOrganizatsiya {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Kod_upr_org;
    @Column(name = "name_org")
    private String nameOrg;
    @Column(name = "Litsevoy_schet")
    private int litsevoySchet;
    @Column(name = "Gorod_org", nullable = false)
    private String gorodOrg;
    @Column(name = "Ulitsa_org", nullable = false)
    private String ulitsaOrg;
    @Column(name = "Nomer_zdaniya_org", nullable = false)
    private String nomerZdaniyaOrg;
    @Transient
    private String adressOrg;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Oplata> oplataSet;


    @Override
    public String toString() {
        return "UprOrganizatsiya{" +
                "Kod_upr_org=" + Kod_upr_org +
                ", nameOrg='" + nameOrg + '\'' +
                ", litsevoySchet=" + litsevoySchet +
                ", gorodOrg='" + gorodOrg + '\'' +
                ", ulitsaOrg='" + ulitsaOrg + '\'' +
                ", nomerZdaniyaOrg='" + nomerZdaniyaOrg + '\'' +
                ", adressOrg='" + adressOrg + '\'' +
                '}';
    }
}
