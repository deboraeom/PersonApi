package com.example.PersonApi.Entity;

import com.example.PersonApi.Enum.BloodType;
import com.example.PersonApi.Enum.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name  = "heath")
@Entity(name  = "Health")
public class Health {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BloodType type;

    @Column
    private String alergy;
}
