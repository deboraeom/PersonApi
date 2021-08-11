package com.example.PersonApi.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name  = "person")
@Entity(name  = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column
    private LocalDate birthDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Phone> phones = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="healthInfo",referencedColumnName = "id")
    private Health healthInfo;
}
