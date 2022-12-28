package com.example.picallti.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String commentaire;
    @ManyToOne
    private User user;
    @ManyToOne
    private Offre offre;
    private String time;
    private String LocalDateTime;


    public Commentaire(String commentaire, User user, Offre offre, LocalTime now, LocalDate now1) {
    }

}
