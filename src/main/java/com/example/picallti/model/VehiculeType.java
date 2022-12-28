package com.example.picallti.model;


import jakarta.persistence.*;

@Entity
public class VehiculeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String url;

    public VehiculeType() {

    }

    public VehiculeType(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public VehiculeType(int id, String name,String url) {
        this.id = id;
        this.name = name;
    }
    public VehiculeType(String name) {
        this.name = name;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

}