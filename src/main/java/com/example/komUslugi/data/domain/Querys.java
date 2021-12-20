package com.example.komUslugi.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Spisok_zaprosov")
@Data
@NoArgsConstructor
public class Querys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="kod_zaprosa")
    private Long id;
    @Column(name = "zapros", length = 2048)
    private String query;
    @ManyToOne
    private User user;
}
