package com.example.picallti.model;

import jakarta.persistence.*;


@Entity
public class Vehicule {
    @Id
    @SequenceGenerator(
            name = "picallti_sequence",
            sequenceName = "picallti_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "picallti_sequence"
    )
    private int id;
    private String marque;

    @ManyToOne()
    private VehiculeType vehiculeType;



    public Vehicule(int id, String marque,VehiculeType vehiculeType) {
        this.id = id;
        this.marque = marque;
        this.vehiculeType = vehiculeType;
    }
    public Vehicule( String marque,VehiculeType vehiculeType) {
        this.marque = marque;
        this.vehiculeType = vehiculeType;
    }
    public Vehicule(String marque) {
        this.marque = marque;
    }

    public Vehicule() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }


    public VehiculeType getVehiculeType() {
        return vehiculeType;
    }

    public void setVehiculeType(VehiculeType vehiculeType) {
        this.vehiculeType = vehiculeType;
    }
}
