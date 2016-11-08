package com.example.a21753725a.magiccardlist;

/**
 * Created by 21753725a on 24/10/16.
 */

public class Card {
    private String name;
    private String type;
    private String rarity;
    private String [] colores;
    private String Fuerza;
    private String texto;
    private String Resistencia;
    private String imagen;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String [] getColores() {
        return colores;
    }

    public void setColores(String [] colores) {
        this.colores = colores;
    }

    public String getFuerza() {
        return Fuerza;
    }

    public void setFuerza(String fuerza) {
        Fuerza = fuerza;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getResistencia() {
        return Resistencia;
    }

    public void setResistencia(String resistencia) {
        Resistencia = resistencia;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    @Override
    public String toString() {
        return "card{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", rarity='" + rarity +
                '}';
    }

}
